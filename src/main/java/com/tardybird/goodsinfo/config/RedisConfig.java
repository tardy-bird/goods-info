package com.tardybird.goodsinfo.config;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author DIX
 * @version 1.0
 * @description
 * @date 2019/12/6 20:27
 */

@Component
public class RedisConfig {

    /**
     * redis缓存失效时间，单位分钟
     */
    @Value("${GoodsInfo.redisExpireTime}")
    private Integer redisExpireTime;

    /**
     * 预提库存数量
     */
    @Value("${GoodsInfo.preDeductQty}")
    private Integer preDeductQty;

    public Integer getRedisExpireTime() {
        return redisExpireTime;
    }

    public Integer getPreDeductQty() {
        return preDeductQty;
    }
}
