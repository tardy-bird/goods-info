package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nick
 */
@Service
public class BrandService {

    @Autowired
    BrandMapper brandMapper;

    public List<Brand> getAllBrands() {
        return brandMapper.getAllBrands();
    }

    public Brand getBrandsById(Long id) {
        return brandMapper.getBrandsById(id);
    }

    public void addBrand(Brand brand) {
        brandMapper.addBrand(brand);
    }

}
