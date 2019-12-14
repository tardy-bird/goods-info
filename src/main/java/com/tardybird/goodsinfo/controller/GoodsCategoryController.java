package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.service.GoodsCategoryService;
import com.tardybird.goodsinfo.util.ResponseUtil;
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
    final
    GoodsCategoryService goodsCategoryService;

    public GoodsCategoryController(GoodsCategoryService goodsCategoryService) {
        this.goodsCategoryService = goodsCategoryService;
    }

    /**
     * xxx 1 u1
     *
     * @return x
     */
    @GetMapping("/categories")
    public Object listGoodsCategory() {
        return ResponseUtil.okList(goodsCategoryService.getAllCategories());
    }

    /**
     * 获取分类详情 3 u4
     */
    @GetMapping("/categories/{id}")
    public Object getSingleCategory(@PathVariable("id") Integer id) {
        return ResponseUtil.ok(goodsCategoryService.getCategory(id));
    }

    /**
     * 获取1级种类 u2
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategory() {
        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelOneCategories();
        return ResponseUtil.okList(goodsCategories);
    }


    /**
     * 获取当前一级分类下的二级分类 u3
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable("id") Integer pid) {
        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelTwoByPid(pid);
        return ResponseUtil.okList(goodsCategories);
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 新建一个分类 2
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        if (goodsCategory == null) {
            return ResponseUtil.badArgument();
        }
        Boolean ok = goodsCategoryService.createCategory(goodsCategory);
        if (!ok) {
            return ResponseUtil.serious();
        }
        return ResponseUtil.ok(goodsCategory);
    }

    /**
     * 修改分类信息 4
     */
    @PutMapping("/categories/{id}")
    public Object updateCategory(@PathVariable("id") Integer id, @RequestBody GoodsCategory goodsCategory) {
        if (goodsCategory == null) {
            return ResponseUtil.badArgument();
        }
        goodsCategory.setId(id);
        Boolean ok = goodsCategoryService.updateCategory(goodsCategory);
        if (!ok) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(goodsCategory);
    }

    /**
     * 删除单个分类 5
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteCategory(@PathVariable("id") Integer id) {
        Boolean ok = goodsCategoryService.deleteCategory(id);
        if (!ok) {
            return ResponseUtil.serious();
        }
        return ResponseUtil.ok();
    }

}
