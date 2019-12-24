package com.tardybird.goodsinfo.task;

import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.ProductPo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nick
 */
@Component
public class WriteObject {

    final RedisTemplate<String, List<String>> redisTemplateOfString;
    final RedisTemplate<String, ProductPo> redisTemplateOfProduct;
    final ProductMapper productMapper;

    public WriteObject(RedisTemplate<String, List<String>> redisTemplateOfString,
                       RedisTemplate<String, ProductPo> redisTemplateOfProduct,
                       ProductMapper productMapper) {
        this.redisTemplateOfString = redisTemplateOfString;
        this.redisTemplateOfProduct = redisTemplateOfProduct;
        this.productMapper = productMapper;
    }

    /**
     * 定时任务，每秒钟将对产库存的更改写回数据库
     */
    @Scheduled(cron = "* * * * * ?")
    private void writeSql() {
        String changeListKey = "Product_Changes";
        List<String> changeList = redisTemplateOfString.opsForValue().get(changeListKey);
        if (changeList != null) {

            for (String key : changeList) {
                String productKey = "Product_" + key;
                ProductPo productPo = redisTemplateOfProduct.opsForValue().get(productKey);
                if (productPo != null) {
                    productMapper.updateProduct(productPo);
                }
            }
        }
        changeList = new ArrayList<>();
        redisTemplateOfString.opsForValue().set(changeListKey, changeList);
    }
}
