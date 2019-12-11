package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.mapper.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nick
 */
@Service
public class BrandService {

    final
    BrandMapper brandMapper;

    public BrandService(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public Object getAllBrands(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Brand> brands = brandMapper.getAllBrands(sort, order);
        return new PageInfo<>(brands);
    }

    public Brand getBrandById(Integer id) {
        return brandMapper.getBrandById(id);
    }

    public Object getBrandsByCondition(String id, String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Brand> brands = brandMapper.getBrandsByCondition(id, name, sort, order);
        return new PageInfo<>(brands);
    }


    public Brand addBrand(Brand brand) {
        Integer id = brandMapper.addBrand(brand);
        brand.setId(id);
        return brand;
    }

    public Boolean deleteBrand(Integer id) {
        return brandMapper.deleteBrand(id);
    }

    public Integer updateBrand(Brand brand) {
        return brandMapper.updateBrand(brand);
    }

}
