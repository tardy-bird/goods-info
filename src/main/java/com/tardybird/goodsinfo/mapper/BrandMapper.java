package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.domain.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface BrandMapper {

    List<Brand> gerAllBrands();

}
