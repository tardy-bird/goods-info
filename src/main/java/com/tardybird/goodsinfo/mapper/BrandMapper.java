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

    /**
     * 查找所有品牌
     * @return
     */
    List<Brand> getAllBrands();

    /**
     * 通过id查品牌
     * @param id
     * @return
     */
    Brand getBrandsById(@Param("id") Integer id);

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    Brand addBrand(@Param("brand") Brand brand);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    boolean deleteBrand(@Param("id") Integer id);
}
