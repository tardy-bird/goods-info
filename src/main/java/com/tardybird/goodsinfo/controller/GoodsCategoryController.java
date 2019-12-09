package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nick
 */
@RestController
public class GoodsCategoryController {
    /*
     * ========= following are wx apis ==============
     */

    /**
     * 获取分类数据
     */

    @Autowired
    GoodsCategoryService goodsCategoryService;

    @GetMapping("/categories")
    public List<GoodsCategory> getAllCategories() {
        return goodsCategoryService.getAllCategories();
    }

    /**
     * 获取分类详情
     */
    @GetMapping("/categories/{id}")
    public GoodsCategory getSingleCategory(@PathVariable("id") Integer id) {
        return goodsCategoryService.getCategory(id);
    }

    /**
     * 获取1级种类
     */
    @GetMapping("/categories/l1")
    public List<GoodsCategory> getFirstCategories() {
        return goodsCategoryService.getLevelOneCategories();
    }


    /**
     * 获取当前一级分类下的二级分类
     */
    @GetMapping("/categories/l1/{id}/l2")
    public List<GoodsCategory> get2rdCategoryOf1st(@PathVariable("id") Integer pid) {
        return goodsCategoryService.getLevelTwoByPid(pid);
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 新建一个分类
     */
    @PostMapping("/categories")
    public int addCategory(@RequestBody GoodsCategory goodsCategory) {
        return goodsCategoryService.createCategory(goodsCategory);
    }

    /**
     * 修改分类信息
     */
    @PutMapping("/categories/{id}")
    public int updateCategory(@PathVariable("id") Integer id,@RequestBody GoodsCategory goodsCategory) {

        goodsCategory.setId(id);
        return goodsCategoryService.updateCategory(goodsCategory);
    }

    /**
     * 删除单个分类
     */
    @DeleteMapping("/categories/{id}")
    public int deleteCategory(@PathVariable("id") Integer id) {
        return goodsCategoryService.deleteCategory(id);
    }

}
