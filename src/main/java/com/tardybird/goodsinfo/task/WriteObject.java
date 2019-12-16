package com.tardybird.goodsinfo.task;

import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.ProductPo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WriteObject {

    final RedisTemplate<String, List<String>> redisTemplateOfString;
    final RedisTemplate<String, ProductPo> redisTemplateOfProduct;
    final ProductMapper productMapper;

    public WriteObject(RedisTemplate<String, List<String>> redisTemplateOfString, RedisTemplate<String, ProductPo> redisTemplateOfProduct, ProductMapper productMapper) {
        this.redisTemplateOfString = redisTemplateOfString;
        this.redisTemplateOfProduct = redisTemplateOfProduct;
        this.productMapper = productMapper;
    }

    @Scheduled(cron = "* * * * * ?")
    private void writeSQL() {
        String changeListKey = "Product_Changes";
        List<String> changeList = redisTemplateOfString.opsForValue().get(changeListKey);
        if (changeList != null) {

            System.out.println("write object(s): " + changeList.size());

            for (String key : changeList) {
                String productKey = "Product_" + key;
                ProductPo productPo = redisTemplateOfProduct.opsForValue().get(productKey);
                if (productPo != null) {
                    System.out.println("updating... " + productPo.getId() + "," + productPo.getSafetyStock());
                    productMapper.updateProduct(productPo);
                }
            }
        }
        changeList = new ArrayList<>();
        redisTemplateOfString.opsForValue().set(changeListKey, changeList);
    }
}
