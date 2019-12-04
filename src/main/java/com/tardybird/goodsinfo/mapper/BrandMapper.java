package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.domain.Brand;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author LENOVO
 */
@Repository
public interface BrandMapper {

    List<Brand> getAllBrands();
    Brand getBrandsById(@Param("id") Integer id);
    Brand addBrand(@Param("brand") Brand brand);
    boolean deleteBrand(@Param("id")Integer id);
}
