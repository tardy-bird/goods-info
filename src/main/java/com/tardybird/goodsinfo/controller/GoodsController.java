package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.client.LogClient;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.Log;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import com.tardybird.goodsinfo.service.GoodsService;
import com.tardybird.goodsinfo.service.ProductService;
import com.tardybird.goodsinfo.util.ObjectConversion;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nick
 */
@RestController
public class GoodsController {

    final GoodsService goodsService;
    final ProductService productService;
    final GoodsDao goodsDao;
    final LogClient logClient;

    public GoodsController(GoodsService goodsService,
                           GoodsDao goodsDao,
                           ProductService productService, LogClient logClient) {
        this.goodsService = goodsService;
        this.goodsDao = goodsDao;
        this.productService = productService;
        this.logClient = logClient;
    }

    /*
     * ========= following are wx apis ==============
     */

    /**
     * 获取商品分类信息
     */
    @GetMapping("/categories/{id}/goods")
    public Object getCategoriesInfoById(@PathVariable("id") Integer id,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer limit) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("获取商品分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.cantFindCategory();
        }

        if (page < 0 || limit < 0) {
            return ResponseUtil.cantFindList();
        }

        List<GoodsPo> goodsPos = goodsService.findGoodsByCategoryId(id, page, limit);

        log = new Log.LogBuilder().type(0).status(1).actions("获取商品分类信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goodsPos);
    }

    /**
     * 获取商品信息列表
     */
    @GetMapping("/goods")
    public Object listGoods(String name,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit) {


        if (page < 0 || limit < 0) {
            return ResponseUtil.cantFind();
        }

        Object goodsList = goodsService.getAllGoodsByConditions(null, name, page, limit);

        return ResponseUtil.ok(goodsList);
    }

    @GetMapping("/admin/goods")
    public Object listAdminGoods(String goodsSn, String name, @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit) {
        Log log;

        if (page < 0 || limit < 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("获取商品分类信息").build();
            logClient.addLog(log);

            return ResponseUtil.cantFindList();
        }

        Object object = goodsService.getAllGoodsByConditions(goodsSn, name, page, limit);

        log = new Log.LogBuilder().type(0).status(1).actions("获取商品分类信息").build();
        logClient.addLog(log);

        return ResponseUtil.ok(object);
    }


    /**
     * 新建/上架一个商品
     */
    @PostMapping("/goods")
    public Object addGoods(@RequestBody GoodsPo goods) {
        Log log;
        if (goods.getName() != null || goods.getGoodsSn() != null || goods.getShortName() != null ||
                goods.getDescription() != null || goods.getBrief() != null || goods.getPicUrl() != null ||
                goods.getDetail() != null || goods.getStatusCode() != null || goods.getGallery() != null ||
                goods.getGoodsCategoryId() != null || goods.getBrandId() != null || goods.getWeight() != null
                || goods.getVolume() != null || goods.getSpecialFreightId() != null) {

            log = new Log.LogBuilder().type(1).status(0).actions("新建/上架一个商品").build();
            logClient.addLog(log);

            GoodsPo goodsPo = goodsService.createGoods(goods);

            if (goodsPo == null) {
                log = new Log.LogBuilder().type(1).status(0).actions("新建/上架一个商品").build();
                logClient.addLog(log);
                return ResponseUtil.failAdd();
            }

            log = new Log.LogBuilder().type(1).status(1).actions("新建/上架一个商品").build();
            logClient.addLog(log);

            return ResponseUtil.ok(goodsPo);
        }
        return ResponseUtil.failAdd();

    }

    /**
     * 根据id获取某个商品
     * 提供一个接口给 collect、comment、footprint 调用,获取 goods 信息
     */
    @GetMapping("/admin/goods/{id}")
    public Object getGoodsByIdAdmin(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("取某个商品").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.cantFindList();
        }

        Goods goods = goodsService.getGoodsByIdAdmin(id);
        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);

        log = new Log.LogBuilder().type(0).status(1).actions("取某个商品").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goodsPo);
    }

    /**
     * @param id x
     * @return x
     */
    @GetMapping("/goods/{id}")
    public Object getGoodsByIdUser(@PathVariable("id") Integer id) {
        if (id <= 0) {
            return ResponseUtil.cantFindList();
        }
        Goods goods = goodsService.getGoodsByIdUser(id);
        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);
        return ResponseUtil.ok(goodsPo);
    }

    /**
     * 管理员查询商品下的产品
     *
     * @param id x
     * @return x
     */
    @GetMapping("/goods/{id}/products")
    public Object listProductByGoodsId(@PathVariable Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("管理员查询商品下的产品").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.cantFindProduct();
        }
        List<ProductPo> products = productService.getProductByGoodsId(id);

        log = new Log.LogBuilder().type(0).status(1).actions("管理员查询商品下的产品").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(products);
    }

    /**
     * 管理员添加商品下的产品
     *
     * @param id      x
     * @param product x
     * @return x
     */
    @PostMapping("/goods/{id}/products")
    public Object addProductByGoodsId(@PathVariable Integer id, @RequestBody ProductPo product) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(2).status(0).actions("添加商品下的产品").actionId(id).build();
            logClient.addLog(log);

            ResponseUtil.cantFind();
        }

//        if (product == null) {
//
//            log = new Log.LogBuilder().type(2).status(0).actions("添加商品下的产品").actionId(id).build();
//            logClient.addLog(log);
//
//            return ResponseUtil.badArgument();
//        }

        Boolean ok = productService.createProduct(product);

        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("添加商品下的产品").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failAddProduct();
        }
        product.setId(id);

        log = new Log.LogBuilder().type(2).status(1).actions("添加商品下的产品").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(product);
    }


    /**
     * 根据id更新商品信息
     */
    @PutMapping("/goods/{id}")
    public Object updateGoodsById(@PathVariable("id") Integer id, @RequestBody GoodsPo goods) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(2).status(0).actions("根据id更新商品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.cantFind();
        }

        goods.setId(id);
        Boolean ok = goodsService.updateGoods(goods);
        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("根据id更新商品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdate();
        }

        log = new Log.LogBuilder().type(2).status(1).actions("根据id更新商品信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goods);
    }

    /**
     * 根据id删除商品信息
     */
    @DeleteMapping("/goods/{id}")
    public Object deleteGoodsById(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(3).status(0).actions("根据id删除商品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.cantFind();
        }
        Boolean ok = goodsService.deleteGoods(id);
        if (!ok) {
            log = new Log.LogBuilder().type(3).status(0).actions("根据id删除商品信息").actionId(id).build();
            logClient.addLog(log);
            return ResponseUtil.failDelete();
        }
        log = new Log.LogBuilder().type(3).status(1).actions("根据id删除商品信息").actionId(id).build();
        logClient.addLog(log);
        return ResponseUtil.ok(id);
    }

    // 内部接口

    @GetMapping("/goods/{id}/isOnSale")
    public Object isGoodsOnSale(@PathVariable Integer id) {
        return goodsService.isGoodsOnSale(id);
    }


}
