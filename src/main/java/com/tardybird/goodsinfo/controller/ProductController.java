package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.client.LogClient;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Log;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.service.ProductService;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author nick
 */
@RestController
public class ProductController {

    final GoodsDao goodsDao;
    final ProductService productService;
    final LogClient logClient;

    private Log log;

    public ProductController(GoodsDao goodsDao, ProductService productService, LogClient logClient) {
        this.goodsDao = goodsDao;
        this.productService = productService;
        this.logClient = logClient;
    }

    /**
     * 根据ID获取产品信息
     *
     * @param id 产品
     * @return 产品信息
     */
    @GetMapping("/products/{id}")
    public Object getProduct(@PathVariable("id") Integer id) {
        if (id <= 0) {
            return ResponseUtil.badArgument();
        }

        Product product = productService.getProductById(id);

        log = new Log.LogBuilder().type(3).status(0).actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(product);
    }


    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id      产品ID
     * @param product 新的产品信息
     * @return Response
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(@PathVariable Integer id,
                                    @RequestBody Product product) {
        if (id <= 0) {
            return ResponseUtil.badArgument();
        }

        // product的这些字段不能同时为空
        if (product.getPicUrl() != null || product.getSpecifications() != null
                || product.getPrice() != null || product.getSafetyStock() != null) {

            log = new Log.LogBuilder().type(2).status(0).actions("管理员修改商品下的某个产品信息").actionId(id).build();
            logClient.addLog(log);

            product.setId(id);
            Boolean ok = productService.updateProduct(product);

            if (!ok) {

                log = new Log.LogBuilder().type(2).status(0).actions("管理员修改商品下的某个产品信息").actionId(id).build();
                logClient.addLog(log);

                return ResponseUtil.failUpdateProduct();
            }

            log = new Log.LogBuilder().type(2).status(1).actions("管理员修改商品下的某个产品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.ok(product);
        }

        return ResponseUtil.failUpdateProduct();
    }

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id 产品
     * @return Response
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id) {

        if (id <= 0) {
            return ResponseUtil.badArgument();
        }

        Log log;
        Boolean ok = productService.deleteProduct(id);

        if (!ok) {

            log = new Log.LogBuilder().type(3).status(0).actions("管理员删除商品下的某个产品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failDeleteProduct();
        }

        log = new Log.LogBuilder().type(3).status(1).actions("管理员删除商品下的某个产品信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(id);
    }

    // 以下是内部接口

    /**
     * 扣库存
     *
     * @param id       产品ID
     * @param quantity 预扣数量
     * @return true 扣成功
     */
    @PutMapping("/product/{id}/deduct")
    public Object deductGoodsQuantity(@PathVariable Integer id,
                                      @RequestParam Integer quantity) {
        if (id <= 0 || quantity <= 0) {
            return false;
        }
        return productService.deductGoodsSafetyStock(id, quantity);
    }

    /**
     * 根据ID获取产品信息
     *
     * @param id 产品
     * @return 产品信息
     */
    @GetMapping("/user/product/{id}")
    public Object getProductInner(@PathVariable("id") Integer id) {
        if (id <= 0) {
            return ResponseUtil.badArgument();
        }

        Product product = productService.getProductById(id);

        log = new Log.LogBuilder().type(3).status(0).actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(product);
    }

}
