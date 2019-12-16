package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.ProductPo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    final RedisTemplate<String, ProductPo> redisTemplateOfProducts;

    final ProductMapper productMapper;

    final RedisTemplate<String, List<String>> redisTemplateOfString;

    public ProductDao(RedisTemplate<String, ProductPo> redisTemplateOfProducts, ProductMapper productMapper, RedisTemplate<String, List<String>> redisTemplateOfString) {
        this.redisTemplateOfProducts = redisTemplateOfProducts;
        this.productMapper = productMapper;
        this.redisTemplateOfString = redisTemplateOfString;
    }

    public Boolean deductProductStock(Integer productId, Integer quantity) {
        String key = "Product_" + productId;

        ProductPo productPo = redisTemplateOfProducts.opsForValue().get(key);

        if (productPo == null) {
            productPo = productMapper.getProductById(productId);
        }

        if (productPo == null) {
            return false;
        }

        redisTemplateOfProducts.opsForValue().set(key, productPo);

        Integer currentStock = productPo.getSafetyStock();
        if (currentStock < quantity) {
            return false;
        }


        productPo.setSafetyStock(currentStock - quantity);

        redisTemplateOfProducts.delete(key);
        redisTemplateOfProducts.opsForValue().set(key, productPo);

        collectChanges(String.valueOf(productPo.getId()));

        return true;
    }

    private void collectChanges(String productId) {
        String productChangeListKey = "Product_Changes";
        List<String> changeList = redisTemplateOfString.opsForValue().get(productChangeListKey);

        if (changeList == null) {
            changeList = new ArrayList<>();
        }

        changeList.add(productId);

        redisTemplateOfString.opsForValue().set(productChangeListKey, changeList);

    }
}
