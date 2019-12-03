package com.tardybird.goodsinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nick
 */
@SpringBootApplication
@MapperScan(basePackages = "com.tardybird.goodsinfo.mapper")
public class GoodsInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsInfoApplication.class, args);
    }

}
