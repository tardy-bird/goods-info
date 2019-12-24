package com.tardybird.goodsinfo.controller;

import com.alibaba.fastjson.JSONObject;
import com.tardybird.goodsinfo.domain.Brand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class BrandControllerTest {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllBrands() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/brands"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getBrandDetails() {
        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/brands/{id}","1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void getBrandsByCondition() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/admins/brands")
                    .param("id","1").param("name","徐亚凤"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void addBrand() {
        try {

            Brand brand = new Brand();
            brand.setName("yujiawei");
            brand.setPicUrl("www.baidu.com");
            brand.setDescription("zjsisdd");
            String str = JSONObject.toJSONString(brand);
            mockMvc.perform(MockMvcRequestBuilders.post("/brands")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(str))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateBrand() {
        try {
            Brand brand =new Brand();
             brand.setName("zjs123");
             brand.setDescription("zjsisdd");
             String str =JSONObject.toJSONString(brand);
             mockMvc.perform(MockMvcRequestBuilders.put("/brands/{id}","1")
                    .content(str)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteBrand() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/brands/{id}","1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}