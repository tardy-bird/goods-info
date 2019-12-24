package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.ProductPo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nick
 */
@Repository
public class ProductDao {

    final RedisTemplate<String, ProductPo> redisTemplateOfProducts;
    final ProductMapper productMapper;
    final RedisTemplate<String, List<String>> redisTemplateOfString;

    public ProductDao(RedisTemplate<String, ProductPo> redisTemplateOfProducts,
                      ProductMapper productMapper,
                      RedisTemplate<String, List<String>> redisTemplateOfString) {
        this.redisTemplateOfProducts = redisTemplateOfProducts;
        this.productMapper = productMapper;
        this.redisTemplateOfString = redisTemplateOfString;
    }

    /**
     * 扣库存
     *
     * @param productId 产品ID
     * @param quantity  预扣数量
     * @return true 扣成功
     */
    public Boolean deductProductStock(Integer productId, Integer quantity) {
        String key = "Product_" + productId;

        ProductPo productPo = redisTemplateOfProducts.opsForValue().get(key);

        // 从数据库中找
        if (productPo == null) {
            productPo = productMapper.getProductById(productId);
        }

        // 如果产品不存在，则扣减失败
        if (productPo == null) {
            return false;
        }

        redisTemplateOfProducts.opsForValue().set(key, productPo);

        // 预期扣减的数量大于实际数量
        Integer currentStock = productPo.getSafetyStock();
        if (currentStock < quantity) {
            return false;
        }

        productPo.setSafetyStock(currentStock - quantity);

        // 更新Redis中关于产品的信息
        redisTemplateOfProducts.delete(key);
        redisTemplateOfProducts.opsForValue().set(key, productPo);

        // 将更改过的产品ID存在一队列中，定时写回数据库
        collectChanges(String.valueOf(productPo.getId()));

        return true;
    }

    /**
     * 收集发生更改的产品ID
     *
     * @param productId 产品ID
     */
    private void collectChanges(String productId) {

        // Redis中的键
        String productChangeListKey = "Product_Changes";
        List<String> changeList = redisTemplateOfString.opsForValue().get(productChangeListKey);

        if (changeList == null) {
            changeList = new ArrayList<>();
        }

        changeList.add(productId);

        redisTemplateOfString.opsForValue().set(productChangeListKey, changeList);

    }
}
