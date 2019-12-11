package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.entity.Brand;
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
     */
    List<Brand> getAllBrands(String sort, String order);

    /**
     * 通过id查品牌
     *
     * @param id xxx
     * @return xxx
     */
    Brand getBrandById(@Param("id") Integer id);

    /**
     * x
     *
     * @param id    x
     * @param name  x
     * @param sort  x
     * @param order x
     * @return x
     */
    List<Brand> getBrandsByCondition(String id, String name, String sort, String order);

    /**
     * 添加品牌
     *
     * @param brand xxx
     * @return xxx
     */
    Integer addBrand(Brand brand);

    /**
     * 删除品牌
     *
     * @param id xxx
     * @return xxx
     */
    boolean deleteBrand(@Param("id") Integer id);

    /**
     * 更新商品
     *
     * @param brand xxx
     * @return xxx
     */
    int updateBrand(Brand brand);
}
