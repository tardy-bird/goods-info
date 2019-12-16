package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DIX
 */
@Repository
public interface GoodsCategoryMapper {

    /**
     * 获得所有分类
     *
     * @return x
     */
    List<GoodsCategoryPo> getAllCategories();

    /**
     * 获得一级分类
     *
     * @return x
     */
    List<GoodsCategoryPo> getLevelOneCategories();

    /**
     * 获得一个分类信息
     *
     * @param id x
     * @return x
     */
    GoodsCategoryPo getCategory(@Param("id") Integer id);

    /**
     * 获得一个一级分类的二级分类
     *
     * @param pid x
     * @return x
     */
    List<GoodsCategoryPo> getLevelTwoByPid(@Param("pid") Integer pid);

    /**
     * 删除分类
     *
     * @param id x
     * @return x
     */
    Integer deleteCategory(@Param("id") Integer id);

    /**
     * 更新分类
     *
     * @param goodsCategory x
     * @return x
     */
    Integer updateCategory(GoodsCategoryPo goodsCategory);

    /**
     * 新建分类
     *
     * @param goodsCategory x
     * @return x
     */
    Integer createCategory(GoodsCategoryPo goodsCategory);

    /**
     * 级联删除
     * @return
     */
    Integer deleteL2withL1(@Param("pid") Integer pid);
}
