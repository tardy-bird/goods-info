package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.po.BrandPo;
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
    List<BrandPo> getAllBrands(String sort, String order);

    /**
     * 通过id查品牌
     *
     * @param id xxx
     * @return xxx
     */
    BrandPo getBrandById(@Param("id") Integer id);

    /**
     * x
     *
     * @param id    x
     * @param name  x
     * @param sort  x
     * @param order x
     * @return x
     */
    List<BrandPo> getBrandsByCondition(String id, String name, String sort, String order);

    /**
     * 添加品牌
     *
     * @param brand xxx
     * @return xxx
     */
    Integer addBrand(BrandPo brand);

    /**
     * 删除品牌
     *
     * @param id xxx
     * @return xxx
     */
    Integer deleteBrand(@Param("id") Integer id);

    /**
     * 更新商品
     *
     * @param brand xxx
     * @return xxx
     */
    Integer updateBrand(BrandPo brand);
}
