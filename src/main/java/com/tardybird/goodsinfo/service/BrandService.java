package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.dao.GoodsDao;
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

    @Autowired
    GoodsDao goodsDao;

    public List<Brand> getAllBrands() {
        return brandMapper.getAllBrands();
    }

    public Brand getBrandsById(Integer id) {
        return brandMapper.getBrandsById(id);
    }

    public void addBrand(Brand brand) {
        brandMapper.addBrand(brand);
    }

    public void deleteBrand(Integer id) {
        brandMapper.deleteBrand(id);
    }

    public void updateBrand(Brand brand) {
        brandMapper.updateBrand(brand);
    }

}
