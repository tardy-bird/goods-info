package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.domain.Brand;
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

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /*
     * ========= following are wx apis ==============
     */

    /**
     * 查看所有品牌
     */
    @GetMapping("/brands")
    public Object getAllBrands(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit,
                               @Sort @RequestParam(defaultValue = "gmt_create") String sort,
                               @Order @RequestParam(defaultValue = "desc") String order) {
        if (page < 0 || limit < 0 || sort == null || order == null) {
            return ResponseUtil.badArgument();
        }
        Object brands = brandService.getAllBrands(page, limit, sort, order);
        return ResponseUtil.ok(brands);
    }

    /**
     * 查看品牌详情
     */
    @GetMapping("/brands/{id}")
    public Object getBrandDetails(@NotNull @PathVariable("id") Integer id) {
        Brand brand = brandService.getBrandById(id);
        return ResponseUtil.ok(brand);
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 根据条件搜索品牌
     */
    @GetMapping("/admins/brands")
    public Object getBrandsByCondition(@NotNull String id, String name,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (id == null || name == null || page < 0 || limit < 0) {
            return ResponseUtil.badArgument();
        }
        Object brands = brandService.getBrandsByCondition(id, name, page, limit, sort, order);
        return ResponseUtil.ok(brands);
    }

    private String handleUploadPicture(MultipartFile file) {
        String path = "/var/www/tardybird/upload/"
                + IdUtil.getValue(8)
                + file.getOriginalFilename();
        String ok = "Success";
        if (ok.equals(FileUploadUtil.upload(file, path))) {
            return path;
        }
        return null;
    }

    /**
     * 创建一个品牌
     */
    @PostMapping("/brands")
    public Object addBrand(@RequestBody Brand brand) {
        // TODO 处理图片URL
        if (brand == null) {
            return ResponseUtil.fail();
        }
        Brand resultBrand = brandService.addBrand(brand);
        return ResponseUtil.ok(resultBrand);
    }

    /**
     * 修改单个品牌的信息
     */
    @PutMapping("/brands/{id}")
    public Object updateBrand(@NotNull @PathVariable("id") Integer id,
                              @RequestBody Brand brand) {
        // TODO 处理图片URL
        if (brand == null) {
            return ResponseUtil.fail();
        }
        brand.setId(id);
        brandService.updateBrand(brand);
        return ResponseUtil.ok(brand);
    }

    /**
     * 删除一个品牌
     */
    @DeleteMapping("/brands/{id}")
    public Object deleteBrand(@PathVariable("id") Integer id) {
        brandService.deleteBrand(id);
        return ResponseUtil.ok();
    }

}
