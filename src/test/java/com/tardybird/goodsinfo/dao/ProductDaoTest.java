package com.tardybird.goodsinfo.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest

public class ProductDaoTest {
    @Autowired
    ProductDao productDao;
    @BeforeEach
    void setup(){

    }
    @AfterEach
    void teardown(){

    }
    @Test
    void deductProductStock(){
        Integer id = 1;
        Integer quantity=10;
        assertEquals(true,productDao.deductProductStock(id,quantity));
    }
}
