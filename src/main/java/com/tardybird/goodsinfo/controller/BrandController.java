package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.client.LogClient;
import com.tardybird.goodsinfo.domain.Log;
import com.tardybird.goodsinfo.po.BrandPo;
import com.tardybird.goodsinfo.service.BrandService;
import com.tardybird.goodsinfo.service.GoodsService;
import com.tardybird.goodsinfo.util.FileUploadUtil;
import com.tardybird.goodsinfo.util.IdUtil;
import com.tardybird.goodsinfo.util.ResponseUtil;
import com.tardybird.goodsinfo.validator.Order;
import com.tardybird.goodsinfo.validator.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author nick
 */
@RestController
public class BrandController {

    final
    BrandService brandService;
    final GoodsService goodsService;

    final
    LogClient logClient;

    public BrandController(BrandService brandService, GoodsService goodsService, LogClient logClient) {
        this.brandService = brandService;
        this.goodsService = goodsService;
        this.logClient = logClient;
    }

    /**
     * 查看所有品牌 u1
     */
    @GetMapping("/brands")
    public Object listBrand(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            @Sort @RequestParam(defaultValue = "gmt_create") String sort,
                            @Order @RequestParam(defaultValue = "desc") String order) {

        if (page == null || limit == null || sort == null || order == null) {
            return ResponseUtil.cantFindListBrand();
        }

        String desc = "desc";
        String asc = "asc";
        if (page >= 0 || limit > 0 || desc.equalsIgnoreCase(order) || asc.equalsIgnoreCase(order)) {
            Object brands = brandService.getAllBrands(page, limit, sort, order);
            return ResponseUtil.ok(brands);
        }

        return ResponseUtil.cantFindListBrand();

    }

    /**
     * 查看品牌详情 3 u2
     */
    @GetMapping("/brands/{id}")
    public Object getBrandDetails(@NotNull @PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {
            log = new Log.LogBuilder().type(0).actions("查看品牌详情").status(0).actionId(id).build();
            logClient.addLog(log);
            return ResponseUtil.cantFindBrand();
        }

        BrandPo brand = brandService.getBrandById(id);

        log = new Log.LogBuilder().type(0).actions("查看品牌详情").status(1).actionId(id).build();
        logClient.addLog(log);
        return ResponseUtil.ok(brand);
    }

    @GetMapping("/admin/brands/{id}")
    public Object getBrandDetailsOfAdmin(@NotNull @PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {
            log = new Log.LogBuilder().type(0).actions("查看品牌详情").status(0).actionId(id).build();
            logClient.addLog(log);
            return ResponseUtil.cantFindBrand();
        }

        BrandPo brand = brandService.getBrandById(id);

        log = new Log.LogBuilder().type(0).actions("查看品牌详情").status(1).actionId(id).build();
        logClient.addLog(log);
        return ResponseUtil.ok(brand);
    }

    /**
     * 根据条件搜索品牌 1
     */
    @GetMapping("/admin/brands")
    public Object getBrandsByCondition(@RequestParam Integer BrandId, @RequestParam String BrandName,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       @Sort @RequestParam(defaultValue = "gmt_create") String sort,
                                       @Order @RequestParam(defaultValue = "desc") String order) {
        Log log;
        if (page == null || limit == null) {
            log = new Log.LogBuilder().type(0).actions("根据条件搜索品牌").status(0)
                    .actionId(BrandId).build();
            logClient.addLog(log);
            return ResponseUtil.cantFindListBrand();
        }

        if (page < 0 || limit < 0) {
            log = new Log.LogBuilder().type(0).actions("根据条件搜索品牌")
                    .status(0).actionId(BrandId).build();
            logClient.addLog(log);
            return ResponseUtil.cantFindListBrand();
        }

        Object brands = brandService.getBrandsByCondition(String.valueOf(BrandId), BrandName, page, limit, sort, order);

        log = new Log.LogBuilder().type(0).actions("根据条件搜索品牌").status(1).build();
        logClient.addLog(log);

        return ResponseUtil.ok(brands);
    }

    /**
     * 创建一个品牌 2
     */
    @PostMapping("/brands")
    public Object addBrand(@RequestBody BrandPo brand) {
        Log log;
        if (brand.getDescription() != null || brand.getPicUrl() != null || brand.getName() != null) {

            log = new Log.LogBuilder().type(1).actions("创建一个品牌").status(0).build();
            logClient.addLog(log);

            return ResponseUtil.failAddBrand();
        }

        Boolean ok = brandService.addBrand(brand);
        if (!ok) {

            log = new Log.LogBuilder().type(1).actions("创建一个品牌").status(0).build();
            logClient.addLog(log);

            return ResponseUtil.failAddBrand();
        }

        log = new Log.LogBuilder().type(1).actions("创建一个品牌").status(1).build();
        logClient.addLog(log);

        return ResponseUtil.ok(brand);
    }


    /**
     * 修改单个品牌的信息 4
     */
    @PutMapping("/brands/{id}")
    public Object updateBrandById(@NotNull @PathVariable("id") Integer id,
                                  @RequestBody BrandPo brand) {

        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(0).actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdateBrand();
        }
        if (brand.getName() != null || brand.getPicUrl() != null || brand.getDescription() != null) {
            brand.setId(id);
            Boolean ok = brandService.updateBrand(brand);
            if (!ok) {
                log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(0).build();
                logClient.addLog(log);
                return ResponseUtil.failUpdateBrand();
            }

            log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(1).build();
            logClient.addLog(log);

            brand = brandService.getBrandById(id);

            return ResponseUtil.ok(brand);
        }


        log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(0).build();
        logClient.addLog(log);
        return ResponseUtil.failUpdateBrand();

    }

    @GetMapping("/brands/{id}/goods")
    public Object findGoodsOfBrand(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {
            return ResponseUtil.cantFindList();
        }

        Object goods = brandService.getGoodsByBrandId(id);
        log = new Log.LogBuilder().type(0).actions("查找品牌对应的商品").actionId(id).status(1).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goods);
    }

    /**
     * 删除一个品牌 5
     */
    @DeleteMapping("/brands/{id}")
    public Object deleteBrandById(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(3).actions("删除一个品牌").status(0).actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failDeleteBrand();
        }
        Boolean ok = brandService.deleteBrand(id);
        if (!ok) {

            log = new Log.LogBuilder().type(3).actions("删除一个品牌").status(0).actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failDeleteBrand();
        }

        log = new Log.LogBuilder().type(3).actions("删除一个品牌").status(1).actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(null);
    }

}
