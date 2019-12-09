package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.service.GoodsService;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author nick
 */
@RestController
public class GoodsController {

    final
    GoodsService goodsService;

    final
    GoodsDao goodsDao;

    public GoodsController(GoodsService goodsService, GoodsDao goodsDao) {
        this.goodsService = goodsService;
        this.goodsDao = goodsDao;
    }
    /*
     * ========= following are wx apis ==============
     */

    /**
     * 获取商品分类信息
     */
    @GetMapping("/categories/{id}/goods")
    public Object getGoodsCategory(@PathVariable("id") Integer id) {
        Goods goods = goodsService.getGoodsById(id);
        GoodsCategory goodsCategory = goods.getGoodsCategory();
        return ResponseUtil.ok(goodsCategory);
    }

    /**
     * 获取商品信息列表
     */
    @GetMapping("/goods")
    public Object getGoodsList(String goodsSn, String name,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit) {
        if (page == null || limit == null || page < 0 || limit < 0) {
            return ResponseUtil.badArgument();
        }
        Object goodsList = goodsService.getAllGoodsByConditions(goodsSn, name, page, limit);
        return ResponseUtil.ok(goodsList);
    }

    @GetMapping("/admins/goods")
    public Object getAllGoods(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer limit) {
        if (page == null || limit == null || page < 0 || limit < 0) {
            return ResponseUtil.badArgument();
        }
        Object object = goodsService.getAllGoodsByConditions(null, null, page, limit);
        return ResponseUtil.ok(object);
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
    public Object addGoods(@RequestBody Goods goods) {
        if (goods == null) {
            return ResponseUtil.badArgument();
        }
        goodsService.createGoods(goods);
        return ResponseUtil.ok(goods);
    }

    /**
     * 根据id获取某个商品
     */
    @GetMapping("/goods/{id}")
    public Object getGoods(@PathVariable("id") Integer id) {
        Goods goods = goodsService.getGoodsById(id);
        return ResponseUtil.ok(goods);
    }

    /**
     * 根据id更新商品信息
     */
    @PutMapping("/goods/{id}")
    public Object updateGoods(@PathVariable("id") Integer id, @RequestBody Goods goods) {
        if (goods == null) {
            return ResponseUtil.badArgument();
        }
        goods.setId(id);
        goodsService.updateGoods(goods);
        return ResponseUtil.ok(goods);
    }

    /**
     * 根据id删除商品信息
     */
    @DeleteMapping("/goods/{id}")
    public Object deleteGoods(@PathVariable("id") Integer id) {
        Boolean status = goodsService.deleteGood(id);
        return ResponseUtil.ok(status);
    }

}
