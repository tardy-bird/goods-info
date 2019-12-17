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
@RequestMapping("/goodsInfoService")
public class ProductController {

    final GoodsDao goodsDao;
    final ProductService productService;
    final LogClient logClient;

    public ProductController(GoodsDao goodsDao, ProductService productService, LogClient logClient) {
        this.goodsDao = goodsDao;
        this.productService = productService;
        this.logClient = logClient;
    }

    @GetMapping("/products/{id}")
    public Object getProduct(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);

        Log log;
        log = new Log.LogBuilder().type(3).status(0).actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(product);
    }

    /**
     * 管理员修改商品下的某个产品信息
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(@PathVariable Integer id,
                                    @RequestBody Product product) {
        Log log;
        if (product == null) {

            log = new Log.LogBuilder().type(2).status(0).actions("管理员修改商品下的某个产品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        product.setId(id);
        Boolean ok = productService.updateProduct(product);
        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("管理员修改商品下的某个产品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.updatedDataFailed();
        }

        log = new Log.LogBuilder().type(2).status(1).actions("管理员修改商品下的某个产品信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(product);
    }

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id x
     * @return x
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id) {
        Log log;
        Boolean ok = productService.deleteProduct(id);
        if (!ok) {

            log = new Log.LogBuilder().type(3).status(0).actions("管理员删除商品下的某个产品信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.updatedDataFailed();
        }

        log = new Log.LogBuilder().type(3).status(1).actions("管理员删除商品下的某个产品信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok();
    }

    @PutMapping("/product/{id}/deduct")
    public Object deductGoodsQuantity(@PathVariable Integer id,
                                      @RequestParam Integer quantity) {
        return productService.deductGoodsSafetyStock(id, quantity);
    }
}
