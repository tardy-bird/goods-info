package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.config.RedisConfig;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisConfig redisConfig;

    /**
     * 通过id获取product
     * @param id
     * @return
     */
    public Product getProductById(Integer id)
    {
        String key="P_"+id;
        Product product=(Product)redisTemplate.opsForValue().get(key);
        if(product==null)
        {
            product=productMapper.getProductById(id);
            redisTemplate.opsForValue().set(key,product,redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
        }
        return product;
    }

    /**
     * 返回一个goods的所有product
     * @param id
     * @return
     */
    public List<Product> getProductByGoodsId(Integer id)
    {
        return productMapper.getProductByGoodsId(id);
    }

    /**
     * 通过id获得商品（包括他的product）
     * @param id
     * @return
     */
    public Goods getGoodsById(Integer id)
    {
        long startTime=System.currentTimeMillis();
        String key="G_"+id;
        Goods goods =(Goods) redisTemplate.opsForValue().get(key);
        System.out.println(key);
        if(goods==null)
        {
            System.out.println(key);
            goods=goodsMapper.getGoodsById(id);
            List<Product> goodsProduct=this.getProductByGoodsId(id);
//            for(Product item:goodsProduct){
//                System.out.println("1"+item.toString());}
            goods.setProductList(goodsProduct);
            redisTemplate.opsForValue().set(key, goods,redisConfig.getRedisExpireTime(),TimeUnit.MINUTES);
            //,redisConfig.getRedisExpireTime(),TimeUnit.MINUTES
        }
        long endTime=System.currentTimeMillis();
        System.out.println(key);
        System.out.println(endTime-startTime);
        return goods;
    }
}
