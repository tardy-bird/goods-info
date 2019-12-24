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
     * @param page 页数
     * @param limit 每页大小
     * @return 所有分类
     */
    List<GoodsCategoryPo> getAllCategories(Integer page,Integer limit);

    /**
     * 获得一级分类
     *
     * @return 一级分类
     */
    List<GoodsCategoryPo> getLevelOneCategories();

    /**
     * 获得一个分类信息
     *
     * @param id 分类ID
     * @return 分类信息
     */
    GoodsCategoryPo getCategory(@Param("id") Integer id);

    /**
     * 获得一个一级分类的二级分类
     *
     * @param pid 一级分类ID
     * @return 二级分类
     */
    List<GoodsCategoryPo> getLevelTwoByPid(@Param("pid") Integer pid);

    /**
     * 删除分类
     *
     * @param id 分类
     * @return 改变行数
     */
    Integer deleteCategory(@Param("id") Integer id);

    /**
     * 更新分类
     *
     * @param goodsCategory 新分类
     * @return 新分类
     */
    Integer updateCategory(GoodsCategoryPo goodsCategory);

    /**
     * 新建分类
     *
     * @param goodsCategory 分类
     * @return 新分类
     */
    Integer createCategory(GoodsCategoryPo goodsCategory);

    /**
     * 级联删除
     * @param pid 分类ID
     * @return 改变行数
     */
    Integer deleteL2withL1(@Param("pid") Integer pid);

    /**
     * 单独修改子分类在父分类中的位置
     * @param goodsCategoryPo 父分类
     * @return 改变行数
     */
    Integer updateParentCategory(GoodsCategoryPo goodsCategoryPo);
}
