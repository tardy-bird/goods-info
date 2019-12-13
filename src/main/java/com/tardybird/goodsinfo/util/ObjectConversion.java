package com.tardybird.goodsinfo.util;

import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.po.BrandPo;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.po.ProductPo;

public class ObjectConversion {

    public static BrandPo brand2BrandPo(Brand brand) {
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

    public static Brand brandPo2Brand(BrandPo brandPo) {
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

    public static Product productPo2Product(ProductPo productPo) {
        Product product = new Product();

        product.setId(productPo.getId());
        product.setPicUrl(productPo.getPicUrl());
        product.setSpecifications(productPo.getSpecifications());
        product.setGoodsId(productPo.getGoodsId());
        product.setPrice(productPo.getPrice());
        product.setSafetyStock(productPo.getSafetyStock());
        product.setGmtCreate(productPo.getGmtCreate());
        product.setGmtModified(productPo.getGmtModified());
        product.setBeDeleted(productPo.getBeDeleted());

        return product;
    }

    public static ProductPo product2ProductPo(Product product) {
        ProductPo productPo = new ProductPo();

        productPo.setId(product.getId());
        productPo.setPicUrl(product.getPicUrl());
        productPo.setSpecifications(product.getSpecifications());
        productPo.setGoodsId(product.getGoodsId());
        productPo.setPrice(product.getPrice());
        productPo.setSafetyStock(product.getSafetyStock());
        productPo.setGmtCreate(product.getGmtCreate());
        productPo.setGmtModified(product.getGmtModified());
        productPo.setBeDeleted(product.getBeDeleted());

        return productPo;
    }

    public static GoodsCategory goodsCategoryPo2GoodsCategory(GoodsCategoryPo goodsCategoryPo) {
        GoodsCategory goodsCategory = new GoodsCategory();

        goodsCategory.setId(goodsCategoryPo.getId());
        goodsCategory.setName(goodsCategoryPo.getName());
        goodsCategory.setPid(goodsCategoryPo.getPid());
        goodsCategory.setGmtCreate(goodsCategoryPo.getGmtCreate());
        goodsCategory.setGmtModified(goodsCategoryPo.getGmtModified());
        goodsCategory.setBeDeleted(goodsCategoryPo.getBeDeleted());

        return goodsCategory;
    }

    public static GoodsCategoryPo goodsCategory2GoodsCategoryPo(GoodsCategory goodsCategory) {
        GoodsCategoryPo goodsCategoryPo = new GoodsCategoryPo();

        goodsCategoryPo.setId(goodsCategory.getId());
        goodsCategoryPo.setName(goodsCategory.getName());
        goodsCategoryPo.setPid(goodsCategory.getPid());
        goodsCategoryPo.setGmtCreate(goodsCategory.getGmtCreate());
        goodsCategoryPo.setGmtModified(goodsCategory.getGmtModified());
        goodsCategoryPo.setBeDeleted(goodsCategory.getBeDeleted());

        return goodsCategoryPo;
    }
}
