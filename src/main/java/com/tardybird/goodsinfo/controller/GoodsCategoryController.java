package com.tardybird.goodsinfo.controller;

import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/categories")
    public Object getAllCategories() {
        return null;
    }

    /**
     * 获取分类详情
     */
    @GetMapping("/categories/{id}")
    public Object getSingleCategory(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 获取1级种类
     */
    @GetMapping("/categories/l1")
    public Object getFirstCategories() {
        return null;
    }

    /**
     * 获取2级种类
     */
    @GetMapping("/categories/l2")
    public Object getSecondCategories() {
        return null;
    }

    /**
     * 获取当前一级分类下的二级分类
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object get2rdCategoryOf1st(@PathVariable("id") Long id) {
        return null;
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 新建一个分类
     */
    @PostMapping("/categories")
    public Object addCategory() {
        return null;
    }

    /**
     * 修改分类信息
     */
    @PutMapping("/categories/{id}")
    public Object updateCategory(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 删除单个分类
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteCategory(@PathVariable("id") Long id) {
        return null;
    }

}
