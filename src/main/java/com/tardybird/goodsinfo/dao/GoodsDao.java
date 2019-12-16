package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/6 19:39
 */

@Repository
public class GoodsDao {

    final ProductMapper productMapper;
    final GoodsMapper goodsMapper;
    final RedisTemplate<String, GoodsPo> redisTemplateOfGoods;
    final RedisTemplate<String, ProductPo> redisTemplateOfProducts;
    final RedisTemplate<String, List<String>> redisTemplateOfString;


    public GoodsDao(RedisTemplate<String, GoodsPo> redisTemplateOfGoods,
                    RedisTemplate<String, ProductPo> redisTemplateOfProducts,
                    RedisTemplate<String, List<String>> redisTemplateOfString,
                    ProductMapper productMapper, GoodsMapper goodsMapper) {
        this.redisTemplateOfGoods = redisTemplateOfGoods;
        this.redisTemplateOfProducts = redisTemplateOfProducts;
        this.redisTemplateOfString = redisTemplateOfString;
        this.productMapper = productMapper;
        this.goodsMapper = goodsMapper;
    }


    @Transactional
    public List<ProductPo> getProductByGoodsId(Integer id) {
        String key = "Goods_Product_" + id;
        List<String> productIds = redisTemplateOfString.opsForValue().get(key);
        if (productIds == null) {
            List<Integer> productIdsByGoodsId = productMapper.findProductIdsByGoodsId(id);

            productIds = new ArrayList<>();
            for (Integer productId : productIdsByGoodsId) {
                productIds.add(productId.toString());
            }

            redisTemplateOfString.opsForValue().set(key, productIds);
        }
        List<ProductPo> productPoList = new ArrayList<>();
        for (String productId : productIds) {
            String productKey = "Product_" + productId;
            ProductPo productPo = redisTemplateOfProducts.opsForValue().get(productKey);
            if (productPo == null) {
                productPo = productMapper.getProductById(Integer.valueOf(productId));
            }
            productPoList.add(productPo);
        }
        return productPoList;
    }


    public ProductPo findProductById(Integer id) {
        String key = "Product_" + id;
        ProductPo product = redisTemplateOfProducts.opsForValue().get(key);
        if (product == null) {

            product = productMapper.getProductById(id);
            redisTemplateOfProducts.opsForValue().set(key, product);

        }

        return product;
    }

    /**
     * 通过id获得商品（包括他的product）
     *
     * @param id x
     * @return x
     */
    public GoodsPo getGoodsByIdAdmin(Integer id) {
        String key = "Goods_" + id;
        GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(key);
        if (goodsPo == null) {
            goodsPo = goodsMapper.getGoodsByIdAdmin(id);
            redisTemplateOfGoods.opsForValue().set(key, goodsPo);
        }
        return goodsPo;
    }

    /**
     * 用户获取商品信息（不能看到未上架的）
     * @param id
     * @return
     */
    public GoodsPo getGoodsByIdUser(Integer id) {
        String key = "Goods_" + id;
        GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(key);
        if (goodsPo == null) {
            goodsPo = goodsMapper.getGoodsByIdUser(id);
            redisTemplateOfGoods.opsForValue().set(key, goodsPo);
        }
        return goodsPo;
    }

    @PostConstruct
    public void storeHotAndNewObjects() {
        List<GoodsPo> cachedGoodsList = goodsMapper.findNewAndHotGoods();

        for (GoodsPo goods : cachedGoodsList) {
            String key = "Goods_" + goods.getId();
            GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(key);
            if (goodsPo == null) {
                redisTemplateOfGoods.opsForValue().set(key, goods);
            }
        }

    }

}
