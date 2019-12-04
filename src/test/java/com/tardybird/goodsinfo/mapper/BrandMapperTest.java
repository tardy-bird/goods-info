package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.GoodsInfoApplication;
import com.tardybird.goodsinfo.domain.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = GoodsInfoApplication.class)
public class BrandMapperTest {

    @Autowired
    private BrandMapper brandMapper;
    @Test
    public List<Brand> getAllBrand(){
        return brandMapper.getAllBrands();
    }
}
