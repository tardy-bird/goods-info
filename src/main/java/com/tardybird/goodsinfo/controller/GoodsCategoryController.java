package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.service.GoodsCategoryService;
import com.tardybird.goodsinfo.util.ResponseUtil;
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
    public Object getAllCategories() {
        return ResponseUtil.okList(goodsCategoryService.getAllCategories());
    }

    /**
     * 获取分类详情
     */
    @GetMapping("/categories/{id}")
    public Object getSingleCategory(@PathVariable("id") Integer id) {
        return ResponseUtil.ok(goodsCategoryService.getCategory(id));
    }

    /**
     * 获取1级种类
     */
    @GetMapping("/categories/l1")
    public Object getFirstCategories() {
        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelOneCategories();
        return ResponseUtil.okList(goodsCategories);
    }


    /**
     * 获取当前一级分类下的二级分类
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object get2rdCategoryOf1st(@PathVariable("id") Integer pid) {
        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelTwoByPid(pid);
        return ResponseUtil.okList(goodsCategories);
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 新建一个分类
     */
    @PostMapping("/categories")
    public Object addCategory(@RequestBody GoodsCategory goodsCategory) {
        goodsCategoryService.createCategory(goodsCategory);
        return ResponseUtil.ok();
    }

    /**
     * 修改分类信息
     */
    @PutMapping("/categories/{id}")
    public Object updateCategory(@PathVariable("id") Integer id, @RequestBody GoodsCategory goodsCategory) {
        if (goodsCategory == null) {
            return ResponseUtil.badArgument();
        }
        goodsCategory.setId(id);
        goodsCategoryService.updateCategory(goodsCategory);
        return ResponseUtil.ok();
    }

    /**
     * 删除单个分类
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteCategory(@PathVariable("id") Integer id) {
        goodsCategoryService.deleteCategory(id);
        return ResponseUtil.ok();
    }

}
