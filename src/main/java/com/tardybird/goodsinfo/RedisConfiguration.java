package com.tardybird.goodsinfo;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;


/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/6 20:24
 */
@Configuration
public class RedisConfiguration {

//    @Bean
//    RedisTemplate<String, Object> redisTemplate1(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        //打开事务支持
//        template.setEnableTransactionSupport(true);
//        return template;
//    }

    @Bean
    RedisTemplate<String, Goods> redisTemplate1(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Goods> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //打开事务支持
        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    RedisTemplate<String, Product> redisTemplate2(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Product> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //打开事务支持
        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    RedisTemplate<String, List<String>> redisTemplate3(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, List<String>> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //打开事务支持
        template.setEnableTransactionSupport(true);
        return template;
    }
}
