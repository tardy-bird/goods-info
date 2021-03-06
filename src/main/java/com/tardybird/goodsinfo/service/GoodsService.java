package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.client.CommentClient;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import com.tardybird.goodsinfo.util.converter.Converter;
import com.tardybird.goodsinfo.util.converter.GoodsConverter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author nick
 */
@Service
public class GoodsService {

    final GoodsMapper goodsMapper;
    final GoodsCategoryMapper goodsCategoryMapper;
    final GoodsDao goodsDao;
    final ProductMapper productMapper;
    final RedisTemplate<String, GoodsPo> redisTemplateOfGoods;
    final RedisTemplate<String, List<String>> redisTemplateOfString;
    final RedisTemplate<String, ProductPo> redisTemplateOfProduct;
    final CommentClient commentClient;

    private Converter<GoodsPo,Goods> converter = new GoodsConverter();

    public GoodsService(GoodsMapper goodsMapper,
                        GoodsCategoryMapper goodsCategoryMapper,
                        GoodsDao goodsDao, ProductMapper productMapper,
                        RedisTemplate<String, GoodsPo> redisTemplateOfGoods,
                        RedisTemplate<String, List<String>> redisTemplateOfString,
                        RedisTemplate<String, ProductPo> redisTemplateOfProduct,
                        CommentClient commentClient) {
        this.goodsMapper = goodsMapper;
        this.goodsCategoryMapper = goodsCategoryMapper;
        this.goodsDao = goodsDao;
        this.productMapper = productMapper;
        this.redisTemplateOfGoods = redisTemplateOfGoods;
        this.redisTemplateOfString = redisTemplateOfString;
        this.redisTemplateOfProduct = redisTemplateOfProduct;
        this.commentClient = commentClient;
    }


    public Object getAllGoodsByConditions(String goodsSn, String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<GoodsPo> goodsPos = goodsMapper.getGoodsByCondition(goodsSn, name);
        return new PageInfo<>(goodsPos);
    }


    public GoodsPo createGoods(GoodsPo goods) {
        Integer affectedRows = goodsMapper.createGoods(goods);

        if (affectedRows <= 0) {
            return null;
        }

        Integer id = goods.getId();
        goods = goodsMapper.getGoodsByIdAdmin(id);

        return goods;
    }

    public Goods getGoodsByIdAdmin(Integer id) {
        GoodsPo goodsPo = goodsDao.getGoodsByIdAdmin(id);
        return converter.converterFromPo(goodsPo);
    }

    public Goods getGoodsByIdUser(Integer id) {
        GoodsPo goodsPo = goodsDao.getGoodsByIdUser(id);
        return converter.converterFromPo(goodsPo);
    }

    public List<GoodsPo> findGoodsByCategoryId(Integer id, Integer page, Integer limit) {
        return goodsDao.findGoodsByCategoryId(id, page, limit);
    }


    public Boolean updateGoods(GoodsPo goods) {

        Integer affectedRows = goodsMapper.updateGoods(goods);

        Boolean status = affectedRows > 0;

        String goodsKey = "Goods_" + goods.getId();
        GoodsPo cachedGoodsPo = redisTemplateOfGoods.opsForValue().get(goodsKey);

        if (cachedGoodsPo != null) {
            redisTemplateOfGoods.delete(goodsKey);
            redisTemplateOfGoods.opsForValue().set(goodsKey, cachedGoodsPo);
        }

        return status;
    }

    public Boolean deleteGoods(Integer id) {

        String goodsKey = "Goods_" + id;
        GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(goodsKey);

        Integer deleteProductRows = productMapper.updateProductByGoodsId(id);

        String productKeys = "Goods_Product_" + id;
        List<String> productIds = redisTemplateOfString.opsForValue().get(productKeys);

        if (productIds != null) {

            for (String productId : productIds) {
                String productKey = "Product_" + productId;
                ProductPo productPo = redisTemplateOfProduct.opsForValue().get(productKey);

                if (productPo != null) {
                    redisTemplateOfProduct.delete(productKey);

                    // 调用Comment模块删除评论
                    commentClient.deleteComments(productPo.getId());
                }
            }

            redisTemplateOfString.delete(productKeys);

        }

        Integer deleteGoodsRows = goodsMapper.deleteGoods(id);

        if (goodsPo != null) {
            redisTemplateOfGoods.delete(goodsKey);
        }

        return deleteProductRows > 0 && deleteGoodsRows > 0;
    }

    public Boolean isGoodsOnSale(Integer id) {
        Integer rows = goodsMapper.selectOnSaleGoods(id);
        return rows > 0;
    }

}
