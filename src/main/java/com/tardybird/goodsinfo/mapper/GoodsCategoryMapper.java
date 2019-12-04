package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DIX
 */
public interface GoodsCategoryMapper {

    /**
     * 获得所有分类
     * @return
     */
    List<GoodsCategory> getAllCategories();

    /**
     * 获得一级分类
     * @return
     */
    List<GoodsCategory> getLevelOneCategories();

    /**
     * 获得一个分类信息
     * @param id
     * @return
     */
    GoodsCategory getCategory(@Param("id") Integer id);

    /**
     * 获得一个一级分类的二级分类
     * @param pid
     * @return
     */
    List<GoodsCategory> getLevelTwoByPid(@Param("pid") Integer pid);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int deleteCategory(@Param("id") Integer id);

    /**
     * 更新分类
     * @param goodsCategory
     * @return
     */
    int updateCategory(GoodsCategory goodsCategory);

    /**
     * 新建分类
     * @param goodsCategory
     * @return
     */
    int createCategory(GoodsCategory goodsCategory);
}
