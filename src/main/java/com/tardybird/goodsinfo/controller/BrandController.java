package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.controller.vo.BrandVo;
import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.service.BrandService;
import com.tardybird.goodsinfo.util.FileUploadUtil;
import com.tardybird.goodsinfo.util.IdUtil;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nick
 */
@RestController
public class BrandController {

    @Autowired
    BrandService brandService;

    /*
     * ========= following are wx apis ==============
     */

    /**
     * 查看所有品牌
     */
    @GetMapping("/brands")
    public Object getAllBrands() {
        return brandService.getAllBrands();
    }

    /**
     * 查看品牌详情
     */
    @GetMapping("/brands/{id}")
    public Object getBrandDetails(@PathVariable("id") Integer id) {
        return brandService.getBrandsById(id);
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 根据条件搜索品牌
     */
    @GetMapping("/admins/brands")
    public Object getBrandsByCondition() {
        return null;
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
     * transfer BrandVo object to Brand object
     */
    private Brand wrapBrandVo(BrandVo brandVo) {
        Brand brand = new Brand();
        brand.setName(brandVo.getName());
        brand.setDescribe(brandVo.getDescription());
        String url = handleUploadPicture(brandVo.getPicture());
        brand.setPicUrl(url);
        return brand;
    }

    /**
     * 创建一个品牌
     */
    @PostMapping("/brands")
    public Object addBrand(BrandVo brandVo) {
        if (brandVo == null) {
            return ResponseUtil.fail();
        }
        Brand brand = wrapBrandVo(brandVo);
        brandService.addBrand(brand);
        return ResponseUtil.ok();
    }

    /**
     * 修改单个品牌的信息
     */
    @PutMapping("/brands/{id}")
    public Object updateBrand(@PathVariable("id") Integer id,
                              @RequestBody BrandVo brandVo) {
        if (brandVo == null) {
            return ResponseUtil.fail();
        }
        Brand brand = wrapBrandVo(brandVo);
        brand.setId(id);
        brandService.updateBrand(brand);
        return ResponseUtil.ok();
    }

    /**
     * 删除一个品牌
     */
    @DeleteMapping("/brands/{id}")
    public void deleteBrand(@PathVariable("id") Integer id) {
        brandService.deleteBrand(id);
    }

}
