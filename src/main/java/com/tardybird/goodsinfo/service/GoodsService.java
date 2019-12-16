package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author LENOVO
 */
@Service
public class GoodsService {

    final GoodsMapper goodsMapper;
    final GoodsCategoryMapper goodsCategoryMapper;
    final GoodsDao goodsDao;
    final ProductMapper productMapper;
    final RedisTemplate<String, GoodsPo> redisTemplateOfGoods;

    public GoodsService(GoodsMapper goodsMapper,
                        GoodsCategoryMapper goodsCategoryMapper,
                        GoodsDao goodsDao, ProductMapper productMapper, RedisTemplate<String, GoodsPo> redisTemplateOfGoods) {
        this.goodsMapper = goodsMapper;
        this.goodsCategoryMapper = goodsCategoryMapper;
        this.goodsDao = goodsDao;
        this.productMapper = productMapper;
        this.redisTemplateOfGoods = redisTemplateOfGoods;
    }


    public Object getAllGoodsByConditions(String goodsSn, String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<GoodsPo> goodsPos = goodsMapper.getGoodsByCondition(goodsSn, name);
        List<Goods> goodsList = new ArrayList<>();
        for (GoodsPo goodsPo : goodsPos) {
            Goods goods = ObjectConversion.goodsPo2Goods(goodsPo);

            //TODO set actual POs
            goods.setBrandPo(null);
            goods.setGoodsCategoryPo(null);
            goods.setProductPoList(null);
            goods.setGrouponRule(null);
            goods.setShareRule(null);
            goods.setPresaleRule(null);

            goodsList.add(goods);
        }
        return new PageInfo<>(goodsList);
    }


    public Boolean createGoods(Goods goods) {
        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);
        Integer affectedRows = goodsMapper.createGoods(goodsPo);
        return affectedRows > 0;
    }

    public Goods getGoodsByIdAdmin(Integer id) {
        GoodsPo goodsPo = goodsDao.getGoodsByIdAdmin(id);
        return ObjectConversion.goodsPo2Goods(goodsPo);
    }

    public Goods getGoodsByIdUser(Integer id) {
        GoodsPo goodsPo = goodsDao.getGoodsByIdUser(id);
        return ObjectConversion.goodsPo2Goods(goodsPo);
    }

    public List<GoodsPo> findGoodsByCategoryId(Integer id) {
        return goodsDao.findGoodsByCategoryId(id);
    }

    public Boolean updateGoods(Goods goods) {

        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);
        Integer affectedRows = goodsMapper.updateGoods(goodsPo);

        Boolean status = affectedRows > 0;

        String goodsKey = "Goods_" + goods.getId();
        GoodsPo cachedGoodsPo = redisTemplateOfGoods.opsForValue().get(goodsKey);

        if (cachedGoodsPo != null) {
            redisTemplateOfGoods.delete(goodsKey);
            redisTemplateOfGoods.opsForValue().set(goodsKey, cachedGoodsPo);
        }

        return status;
    }

    @Transactional
    public Boolean deleteGood(Integer id) {

        String goodsKey = "Goods_" + id;
        GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(goodsKey);

        Integer deleteProductRows = productMapper.updateProductByGoodsId(id);
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

//    public Boolean deductGoodsVolume(Integer id, Integer quantity) {
//        Integer affectedRows = goodsMapper.deductQuantity(id, quantity);
//        return affectedRows > 0;
//    }
}
