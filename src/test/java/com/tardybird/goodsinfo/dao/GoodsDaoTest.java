package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GoodsDaoTest {
    @Autowired
    GoodsDao goodsDao;
    @BeforeEach
    void  setup(){

    }
    @AfterEach
    void teardown(){

    }
    @Test
    void getProductByGoodsId(){
        Goods goods =new Goods();
        goods.setId(273);
        List<ProductPo> result=goodsDao.getProductByGoodsId(goods.getId());
        assertEquals(1,result.size());
    }
    @Test
    void findGoodsByCategoryId(){
        Integer id = 126;
        Integer page=1;
        Integer limit=10;
        List<GoodsPo> result=goodsDao.findGoodsByCategoryId(id,page,limit);
        assertEquals(10,result.size());
    }
    @Test
    void findProductById(){
        Integer id = 1;
        ProductPo productPo =goodsDao.findProductById(id);
        assertEquals("[\"1.5m床垫*1+枕头*2\",\"浅杏粉\"]",productPo.getSpecifications());
        assertEquals("http://yanxuan.nosdn.127.net/1f67b1970ee20fd572b7202da0ff705d.png",productPo.getPicUrl());
    }
    @Test
    void getGoodsByIdAdmin(){
        Integer id = 300;
        GoodsPo goodsPo =goodsDao.getGoodsByIdAdmin(id);
        assertEquals("金和汇景•赵紫云•粉彩绣球瓷瓶",goodsPo.getName());
        assertEquals("zzy-d0001",goodsPo.getGoodsSn());
    }
}
