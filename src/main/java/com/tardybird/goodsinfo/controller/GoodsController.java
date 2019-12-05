package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.service.GoodsService;
import com.tardybird.goodsinfo.validator.LoginUser;
import com.tardybird.goodsinfo.validator.Order;
import com.tardybird.goodsinfo.validator.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String getGoodsCategory(@PathVariable("id") Integer id) {
        Goods goods = goodsService.getGoodsById(id);
        GoodsCategory goodsCategory = goods.getGoodsCategory();
        return goodsCategory.getName();
    }

    /**
     * 获取商品信息列表
     */
    @GetMapping("/goods")
    public Object getGoodsList(String goodsSn, String name,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit,
                               @Sort @RequestParam(defaultValue = "add_time") String sort,
                               @Order @RequestParam(defaultValue = "desc") String order) {
        return null;
    }

    /**
     * 根据条件搜素商品
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param goodsCategoryId 分类类目ID，可选
     * @param brandId         品牌商ID，可选
     * @param keyword         关键字，可选
     * @param isNew           是否新品，可选
     * @param isHot           是否热买，可选
     * @param userId          用户ID
     * @param page            分页页数
     * @param limit           分页大小
     * @param sort            排序方式，支持"add_time", "retail_price"或"name"
     * @param order           排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     */
    @GetMapping("/goods/searchinformation")
    public Object searchGoodsByConditions(Integer goodsCategoryId,
                                          Integer brandId,
                                          String keyword,
                                          Boolean isNew,
                                          Boolean isHot,
                                          @LoginUser Integer userId,
                                          @RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit,
                                          @Sort(accepts = {"add_time", "retail_price", "name"}) @RequestParam(defaultValue = "add_time") String sort,
                                          @Order @RequestParam(defaultValue = "desc") String order) {
        return null;
    }

    /**
     * 查看在售的商品总数
     */
    @GetMapping("/goodsCounts")
    public Integer getGoodsCounts() {
        return goodsService.getGoodsCount();
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
        if (goods != null) {
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
