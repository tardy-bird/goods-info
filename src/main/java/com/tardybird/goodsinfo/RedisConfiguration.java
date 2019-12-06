package com.tardybird.goodsinfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/6 20:24
 */
@Configuration
public class RedisConfiguration {
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //打开事务支持
        template.setEnableTransactionSupport(true);
        return template;
    }
}
