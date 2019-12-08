package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.domain.Product;
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

    @Autowired
    GoodsDao goodsDao;
    /*
     * ========= following are wx apis ==============
     */

    /**
     * 获取商品分类信息
     */
    @GetMapping("/categories/{id}/goods")
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
                               @RequestParam(defaultValue = "10") Integer limit)
//                               @Sort @RequestParam(defaultValue = "add_time") String sort,
//                               @Order @RequestParam(defaultValue = "desc") String order)
    {
        return goodsService.getAllGoodsByConditions(goodsSn,name,page,limit);
    }

    @GetMapping("/admins/goods")
    public Object getAllGoods(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer limit)
    {
        return goodsService.getAllGoodsIdPic(page, limit);
    }



    /**
     * 查看推荐商品
     */
    @GetMapping("/recommendedGoods")
    public Object getRecommendedGoods() {
        return null;
    }

    /**
     * 用户查看商品（默认hot）
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("users/goods")
    public Object getAllGoodsIdPic(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit)
    {return goodsService.getHotGoodsIdPic(page,limit);}

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
    public boolean deleteGoods(@PathVariable("id") Integer id) {
        return goodsService.deleteGood(id);
    }

    /**
     * 获取分类和品牌
     */
    @GetMapping("/goods/catAndBrand")
    public Object getAllCategoriesAndBrands() {
        return null;
    }
}
