package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.client.LogClient;
import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.domain.Log;
import com.tardybird.goodsinfo.service.BrandService;
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

    final
    LogClient logClient;

    public BrandController(BrandService brandService, LogClient logClient) {
        this.brandService = brandService;
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
            return ResponseUtil.badArgument();
        }

        String desc = "desc";
        String asc = "asc";
        if (page >= 0 || limit > 0 || desc.equalsIgnoreCase(order) || asc.equalsIgnoreCase(order)) {
            Object brands = brandService.getAllBrands(page, limit, sort, order);
            return ResponseUtil.ok(brands);
        }

        return ResponseUtil.badArgumentValue();

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
            return ResponseUtil.badArgumentValue();
        }
        Brand brand = brandService.getBrandById(id);
        log = new Log.LogBuilder().type(0).actions("查看品牌详情").status(1).actionId(id).build();
        logClient.addLog(log);
        return ResponseUtil.ok(brand);
    }

    /**
     * 根据条件搜索品牌 1
     */
    @GetMapping("/admins/brands")
    public Object getBrandsByCondition(Integer id, String name,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                                       @Order @RequestParam(defaultValue = "desc") String order) {
        Log log;
        if (page == null || limit == null) {
            log = new Log.LogBuilder().type(0).actions("根据条件搜索品牌").status(0)
                    .actionId(id).build();
            logClient.addLog(log);
            return ResponseUtil.badArgument();
        }

        if (page < 0 || limit < 0) {
            log = new Log.LogBuilder().type(0).actions("根据条件搜索品牌")
                    .status(0).actionId(id).build();
            logClient.addLog(log);
            return ResponseUtil.badArgumentValue();
        }

        Object brands = brandService.getBrandsByCondition(String.valueOf(id), name, page, limit, sort, order);

        log = new Log.LogBuilder().type(0).actions("根据条件搜索品牌").status(1).build();
        logClient.addLog(log);

        return ResponseUtil.ok(brands);
    }

    @PostMapping("/pics")
    public Object handleUploadPicture(@RequestBody MultipartFile file) {
        if (file == null) {
            return ResponseUtil.badArgument();
        }
        String path = "/var/www/tardybird/upload/"
                + IdUtil.getValue(8)
                + file.getOriginalFilename();
        String ok = "Success";
        if (ok.equals(FileUploadUtil.upload(file, path))) {
            // not recommend (hard code)
            String prefix = "http://47.96.159.71:6180";
            return ResponseUtil.ok(prefix + path);
        }
        return ResponseUtil.fail();
    }

    /**
     * 创建一个品牌 2
     */
    @PostMapping("/brands")
    public Object addBrand(@RequestBody Brand brand) {
        Log log;
        if (brand == null) {

            log = new Log.LogBuilder().type(1).actions("创建一个品牌").status(0).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        Boolean ok = brandService.addBrand(brand);
        if (!ok) {

            log = new Log.LogBuilder().type(1).actions("根据条件搜索品牌").status(0).build();
            logClient.addLog(log);

            return ResponseUtil.serious();
        }

        log = new Log.LogBuilder().type(1).actions("根据条件搜索品牌").status(1).build();
        logClient.addLog(log);

        return ResponseUtil.ok(brand);
    }


    /**
     * 修改单个品牌的信息 4
     */
    @PutMapping("/brands/{id}")
    public Object updateBrandById(@NotNull @PathVariable("id") Integer id,
                                  @RequestBody Brand brand) {

        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(0).actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgumentValue();
        }
        if (brand == null) {
            log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(0).build();
            logClient.addLog(log);
            return ResponseUtil.badArgument();
        }
        brand.setId(id);
        Boolean ok = brandService.updateBrand(brand);
        if (!ok) {
            log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(0).build();
            logClient.addLog(log);
            return ResponseUtil.updatedDataFailed();
        }

        log = new Log.LogBuilder().type(2).actions("修改单个品牌的信息").status(1).build();
        logClient.addLog(log);

        return ResponseUtil.ok(brand);
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

            return ResponseUtil.badArgumentValue();
        }
        Boolean ok = brandService.deleteBrand(id);
        if (!ok) {

            log = new Log.LogBuilder().type(3).actions("删除一个品牌").status(0).actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.serious();
        }

        log = new Log.LogBuilder().type(3).actions("删除一个品牌").status(1).actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok();
    }

}
