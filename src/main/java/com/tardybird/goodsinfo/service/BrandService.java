package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.mapper.BrandMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.po.BrandPo;
import com.tardybird.goodsinfo.po.GoodsPo;
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
    final
    GoodsMapper goodsMapper;

    public BrandService(BrandMapper brandMapper, GoodsMapper goodsMapper) {
        this.brandMapper = brandMapper;
        this.goodsMapper = goodsMapper;
    }

    public Object getAllBrands(Integer page, Integer limit,
                               String sort, String order) {
        PageHelper.startPage(page, limit);

        List<BrandPo> brandPos = brandMapper.getAllBrands(sort, order);

        return getObject(brandPos);
    }

    public Brand getBrandById(Integer id) {
        BrandPo brandPo = brandMapper.getBrandById(id);

        if (brandPo == null) {
            return null;
        }

        List<GoodsPo> goodsPos = goodsMapper.findGoodsByBrandId(String.valueOf(brandPo.getId()));
        Brand brand = ObjectConversion.brandPo2Brand(brandPo);
        brand.setGoodsPoList(goodsPos);

        return brand;
    }

    public Object getBrandsByCondition(String id, String name, Integer page,
                                       Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);

        List<BrandPo> brandPos = brandMapper.getBrandsByCondition(id, name, sort, order);

        return getObject(brandPos);
    }

    private Object getObject(List<BrandPo> brandPos) {
        List<Brand> brandList = new ArrayList<>();
        for (BrandPo brandPo : brandPos) {

            Brand brand = ObjectConversion.brandPo2Brand(brandPo);

            List<GoodsPo> goodsPos = goodsMapper.findGoodsByBrandId(String.valueOf(brandPo.getId()));
            brand.setGoodsPoList(goodsPos);

            brandList.add(brand);
        }
        return new PageInfo<>(brandList);
    }


    public Boolean addBrand(Brand brand) {
        BrandPo brandPo = ObjectConversion.brand2BrandPo(brand);
        Integer affectedRows = brandMapper.addBrand(brandPo);
        brand.setId(brandPo.getId());
        return affectedRows > 0;
    }

    public Boolean deleteBrand(Integer id) {
        Integer affectedRows = brandMapper.deleteBrand(id);
        return affectedRows > 0;
    }

    public Boolean updateBrand(Brand brand) {
        BrandPo brandPo = ObjectConversion.brand2BrandPo(brand);
        Integer affectedRows = brandMapper.updateBrand(brandPo);
        return affectedRows > 0;
    }

}
