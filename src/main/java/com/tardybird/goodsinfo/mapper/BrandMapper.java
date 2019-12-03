package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.domain.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author LENOVO
 */
@Repository
public interface BrandMapper {

    List<Brand> gerAllBrands();

}
