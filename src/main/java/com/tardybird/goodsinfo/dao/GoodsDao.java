package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.config.RedisConfig;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/6 19:39
 */

@Repository
public class GoodsDao {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisConfig redisConfig;

    /**
     * 通过id获取product
     *
     * @param id x
     * @return x
     */
    public Product getProductById(Integer id) {
        String key = "P_" + id;
        Product product = (Product) redisTemplate.opsForValue().get(key);
        if (product == null) {
            product = productMapper.getProductById(id);
            redisTemplate.opsForValue().set(key, product, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
        }
        return product;
    }

    /**
     * 返回一个goods的所有product
     *
     * @param id x
     * @return x
     */
    public List<Product> getProductByGoodsId(Integer id) {
        return productMapper.getProductByGoodsId(id);
    }

    /**
     * 通过id获得商品（包括他的product）
     *
     * @param id x
     * @return x
     */
    public Goods getGoodsById(Integer id) {
        String key = "G_" + id;
        Goods goods = (Goods) redisTemplate.opsForValue().get(key);
        if (goods == null) {
            goods = goodsMapper.getGoodsById(id);
            List<Product> goodsProduct = this.getProductByGoodsId(id);
            goods.setProductList(goodsProduct);
            redisTemplate.opsForValue().set(key, goods, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
        }
        return goods;
    }

    /**
     * 获得所有商品
     *
     * @return x
     */
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

}
