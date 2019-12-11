package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.controller.vo.GoodsVo;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.entity.GoodsCategory;
import com.tardybird.goodsinfo.entity.Product;
import com.tardybird.goodsinfo.service.GoodsService;
import com.tardybird.goodsinfo.service.ProductService;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nick
 */
@RestController
@RequestMapping("/goodsService")
public class GoodsController {

    final
    GoodsService goodsService;

    final
    ProductService productService;

    final
    GoodsDao goodsDao;

    public GoodsController(GoodsService goodsService, GoodsDao goodsDao, ProductService productService) {
        this.goodsService = goodsService;
        this.goodsDao = goodsDao;
        this.productService = productService;
    }
    /*
     * ========= following are wx apis ==============
     */

    /**
     * 获取商品分类信息
     */
    @GetMapping("/categories/{id}/goods")
    public Object getGoodsCategory(@PathVariable("id") Integer id) {
        GoodsVo goodsVo = goodsService.getGoodsById(id);
        GoodsCategory goodsCategory = goodsVo.getGoodsCategory();
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
    @Deprecated
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
        GoodsVo goodsVo = goodsService.getGoodsById(id);
        return ResponseUtil.ok(goodsVo);
    }

    /**
     * 管理员查询商品下的产品
     *
     * @param id x
     * @return x
     */
    @GetMapping("/goods/{id}/products")
    public Object listProductByGoodsId(@PathVariable Integer id) {
        List<Product> products = productService.getProductByGoodsId(id);
        return ResponseUtil.ok(products);
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
