package com.tardybird.goodsinfo;

import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
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

    @Bean
    RedisTemplate<String, GoodsPo> redisTemplate1(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, GoodsPo> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //打开事务支持
        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    RedisTemplate<String, ProductPo> redisTemplate2(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ProductPo> template = new RedisTemplate<>();
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
