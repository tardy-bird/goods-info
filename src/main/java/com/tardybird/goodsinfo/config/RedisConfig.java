package com.tardybird.goodsinfo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/6 20:27
 */

@Component
@Getter
public class RedisConfig {
    /**
     * redis缓存失效时间，单位分钟
     */
    @Value("${goods.redisExpireTime}")
    private Integer redisExpireTime;

    /**
     * 预提库存数量
     */
    @Value("${goods.preDeductQuantity}")
    private Integer preDeductQty;

}
