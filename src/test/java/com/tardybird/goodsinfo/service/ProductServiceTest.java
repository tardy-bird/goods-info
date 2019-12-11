package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProductByGoodsId() {
        Integer goodsId = 100;
        List<Product> productList = productService.getProductByGoodsId(goodsId);
        System.out.println(productList);
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setPicUrl("http://bing.com");
        product.setPrice(BigDecimal.TEN);
        product.setGoodsId(100);
        productService.createProduct(product);
    }

    @Test
    void updateProduct() {
        Product product = new Product();
        product.setPicUrl("this is a new PICTURE");
//        product.setPrice(BigDecimal.valueOf(10.50));

        product.setId(1);

        productService.updateProduct(product);
    }

    @Test
    void getProductById() {
        Integer id = 1;
        Product product = productService.getProductById(id);
        System.out.println(product);
    }

    @Test
    void deleteProduct() {
        Product product = new Product();
        product.setId(3);
        productService.deleteProduct(product.getId());
    }
}