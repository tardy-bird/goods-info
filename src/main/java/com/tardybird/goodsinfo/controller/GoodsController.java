package com.tardybird.goodsinfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nick
 */
@RestController
public class GoodsController {

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
    public Object searchGoodsByConditions() {
        return null;
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

}
