package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.mapper.BrandMapper;
import com.tardybird.goodsinfo.po.BrandPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<BrandPo> brandPos = brandMapper.getAllBrands(sort, order);

        return getObject(brandPos);
    }

    public Brand getBrandById(Integer id) {
        BrandPo brandPo = brandMapper.getBrandById(id);
        return ObjectConversion.brandPo2Brand(brandPo);
    }

    public Object getBrandsByCondition(String id, String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);

        List<BrandPo> brandPos = brandMapper.getBrandsByCondition(id, name, sort, order);

        return getObject(brandPos);
    }

    private Object getObject(List<BrandPo> brandPos) {
        List<Brand> brandList = new ArrayList<>();
        for (BrandPo brandPo : brandPos) {

            Brand brand = ObjectConversion.brandPo2Brand(brandPo);

            //TODO find Goods to add
            brand.setGoodsPoList(null);

            brandList.add(brand);
        }
        return new PageInfo<>(brandList);
    }


    public Brand addBrand(Brand brand) {
        BrandPo brandPo = ObjectConversion.brand2BrandPo(brand);
        Integer id = brandMapper.addBrand(brandPo);
        brand.setId(id);
        return brand;
    }

    public Boolean deleteBrand(Integer id) {
        return brandMapper.deleteBrand(id);
    }

    public Integer updateBrand(Brand brand) {
        BrandPo brandPo = ObjectConversion.brand2BrandPo(brand);
        return brandMapper.updateBrand(brandPo);
    }

}
