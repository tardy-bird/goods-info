package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nick
 */
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;
    /*
     * ========= following are wx apis ==============
     */

    /**
     * 获取商品分类信息
     */
    @GetMapping("/category/{id}")
    public Object getGoodsCategory(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 获取商品信息列表
     */
    @GetMapping("/goods")
    public Object getGoodsList() {
        return null;
    }

    /**
     * 根据条件搜素商品
     */
    @GetMapping("/goods/searchinformation")
    public Goods searchGoodsByConditions(@RequestBody Goods goods) {
        return goodsService.searchGoodsByConditions(goods);
    }

    /**
     * 查看在售的商品总数
     */
    @GetMapping("/goodsCounts")
    public Integer getGoodsCounts() {
        return 0;
    }

    /**
     * 查看推荐商品
     */
    @GetMapping("/recommendedGoods")
    public Object getRecommendedGoods() {
        return null;
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 新建/上架一个商品
     */
    @PostMapping("/goods")
    public boolean addGoods(@RequestBody Goods goods) {
        if (goods != null && !goods.equals("")) {
            return goodsService.createGoods(goods);
        } else {
            return false;
        }
    }

    /**
     * 根据id获取某个商品
     */
    @GetMapping("/goods/{id}")
    public Goods getGoods(@PathVariable("id") Integer id) {
        return goodsService.getGoodsById(id);
    }

    /**
     * 根据id更新商品信息
     */
    @PutMapping("/goods/{id}")
    public Object updateGoods(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 根据id删除商品信息
     */
    @DeleteMapping("/goods/{id}")
    public void deleteGoods(@PathVariable("id") Long id) {
    }

    /**
     * 获取分类和品牌
     */
    @GetMapping("/goods/catAndBrand")
    public Object getAllCategoriesAndBrands() {
        return null;
    }
}
