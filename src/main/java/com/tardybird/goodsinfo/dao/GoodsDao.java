package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DIX
 * @version 1.0
 * @description
 * @date 2019/12/6 19:39
 */

@Repository
public class GoodsDao {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public Product getProductById(Integer id)
    {
        String key="P_"+id;
        Product product=(Product)redisTemplate.opsForValue().get(key);
        if(product==null)
        {
            product=productMapper.getProductById(id);
            redisTemplate.opsForValue().set(key,product);
        }
    }

    public Goods getGoodsById(Integer id)
    {
        String key="G_"+id;
        Goods goods =(Goods) redisTemplate.opsForValue().get(key);
        if(goods==null)
        {
            goods=goodsMapper.getGoodsById(id);
            List<Product> goodsProduct=this.getProductByGoodsId(id);
        }
    }
}
