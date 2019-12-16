package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.domain.Goods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class GoodsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCategoriesInfoById() {
    }

    @Test
    void listGoods() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/goods"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testListGoods() {
    }

    @Test
    void getRecommendedGoods() {
    }

    @Test
    void addGoods() {
    }

    @Test
    void getGoodsById() {
        Goods goods = new Goods();
        goods.setId(1);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/goods/" + goods.getId()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getGoodsByIdUser()
    {
        Goods goods = new Goods();
        goods.setId(2);
        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/goods"+goods.getId()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void listProductByGoodsId() {

        Goods goods = new Goods();
        goods.setId(1);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/goods/" + goods.getId() + "/products"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void addProductByGoodsId() {
    }

    @Test
    void updateGoodsById() {
    }

    @Test
    void deleteGoodsById() {
    }
}