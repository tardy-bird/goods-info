package com.tardybird.goodsinfo.util.converter;

import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.po.BrandPo;

import java.util.function.Function;

/**
 * @author nick
 */
public class BrandConverter extends Converter<BrandPo, Brand> {


    public BrandConverter() {
        super(new BrandFunction(), new BrandPoFunction());
    }

    static class BrandFunction implements Function<BrandPo, Brand> {

        @Override
        public Brand apply(BrandPo brandPo) {
            if (brandPo == null) {
                return null;
            }

            Brand brand = new Brand();

            brand.setId(brandPo.getId());
            brand.setName(brandPo.getName());
            brand.setDescription(brandPo.getDescription());
            brand.setPicUrl(brandPo.getPicUrl());
            brand.setGmtCreate(brandPo.getGmtCreate());
            brand.setGmtModified(brandPo.getGmtModified());
            brand.setBeDeleted(brandPo.getBeDeleted());

            return brand;
        }
    }

    static class BrandPoFunction implements Function<Brand, BrandPo> {

        @Override
        public BrandPo apply(Brand brand) {
            if (brand == null) {
                return null;
            }

            BrandPo brandPo = new BrandPo();

            brandPo.setId(brand.getId());
            brandPo.setName(brand.getName());
            brandPo.setDescription(brand.getDescription());
            brandPo.setPicUrl(brand.getPicUrl());
            brandPo.setGmtCreate(brand.getGmtCreate());
            brandPo.setGmtModified(brand.getGmtModified());
            brandPo.setBeDeleted(brand.getBeDeleted());

            return brandPo;
        }
    }
}
