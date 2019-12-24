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
     * @param sort 排序字段
     * @param order 排序规则
     * @return 所有品牌
     */
    List<BrandPo> getAllBrands(String sort, String order);

    /**
     * 通过id查品牌
     * @param id 品牌ID
     * @return 品牌
     */
    BrandPo getBrandById(@Param("id") Integer id);

    /**
     * 按照条件查询品牌
     *
     * @param id    品牌
     * @param name  品牌名称
     * @param sort 排序字段
     * @param order 排序规则
     * @return 品牌
     */
    List<BrandPo> getBrandsByCondition(String id, String name, String sort, String order);

    /**
     * 添加品牌
     *
     * @param brand 品牌
     * @return 改变行数
     */
    Integer addBrand(BrandPo brand);

    /**
     * 删除品牌
     *
     * @param id 品牌ID
     * @return 改变行数
     */
    Integer deleteBrand(@Param("id") Integer id);

    /**
     * 更新商品
     *
     * @param brand 品牌
     * @return 改变行数
     */
    Integer updateBrand(BrandPo brand);
}
