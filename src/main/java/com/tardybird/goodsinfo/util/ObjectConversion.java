package com.tardybird.goodsinfo.util;

import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.po.BrandPo;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;

public class ObjectConversion {

    public static BrandPo brand2BrandPo(Brand brand) {
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

    public static Brand brandPo2Brand(BrandPo brandPo) {
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

    public static Product productPo2Product(ProductPo productPo) {
        if (productPo == null) {
            return null;
        }

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
        if (product == null) {
            return null;
        }

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
        if (goodsCategoryPo == null) {
            return null;
        }

        GoodsCategory goodsCategory = new GoodsCategory();

        goodsCategory.setId(goodsCategoryPo.getId());
        goodsCategory.setName(goodsCategoryPo.getName());
        goodsCategory.setPid(goodsCategoryPo.getPid());
        goodsCategory.setGmtCreate(goodsCategoryPo.getGmtCreate());
        goodsCategory.setPicUrl(goodsCategoryPo.getPicUrl());
        goodsCategory.setGmtModified(goodsCategoryPo.getGmtModified());
        goodsCategory.setBeDeleted(goodsCategoryPo.getBeDeleted());

        return goodsCategory;
    }

    public static GoodsCategoryPo goodsCategory2GoodsCategoryPo(GoodsCategory goodsCategory) {
        if (goodsCategory == null) {
            return null;
        }

        GoodsCategoryPo goodsCategoryPo = new GoodsCategoryPo();

        goodsCategoryPo.setId(goodsCategory.getId());
        goodsCategoryPo.setName(goodsCategory.getName());
        goodsCategoryPo.setPid(goodsCategory.getPid());
        goodsCategoryPo.setPicUrl(goodsCategory.getPicUrl());
        goodsCategoryPo.setGmtCreate(goodsCategory.getGmtCreate());
        goodsCategoryPo.setGmtModified(goodsCategory.getGmtModified());
        goodsCategoryPo.setBeDeleted(goodsCategory.getBeDeleted());

        return goodsCategoryPo;
    }

    public static Goods goodsPo2Goods(GoodsPo goodsPo) {
        if (goodsPo == null) {
            return null;
        }

        Goods goods = new Goods();

        goods.setId(goodsPo.getId());
        goods.setGmtCreate(goodsPo.getGmtCreate());
        goods.setGmtModified(goodsPo.getGmtModified());
        goods.setName(goodsPo.getName());
        goods.setGoodsSn(goodsPo.getGoodsSn());
        goods.setShortName(goodsPo.getShortName());
        goods.setDescription(goodsPo.getDescription());
        goods.setBrief(goodsPo.getBrief());
        goods.setPicUrl(goodsPo.getPicUrl());
        goods.setDetail(goodsPo.getDetail());
        goods.setStatusCode(goodsPo.getStatusCode());
        goods.setShareUrl(goodsPo.getShareUrl());
        goods.setGallery(goodsPo.getGallery());
        goods.setGoodsCategoryId(goodsPo.getGoodsCategoryId());
        goods.setBrandId(goodsPo.getBrandId());
        goods.setBeDeleted(goodsPo.getBeDeleted());
        goods.setWeight(goodsPo.getWeight());
        goods.setVolume(goodsPo.getVolume());
        goods.setSpecialFreightId(goodsPo.getSpecialFreightId());
        goods.setBeSpecial(goodsPo.getBeSpecial());
        goods.setPrice(goodsPo.getPrice());

        return goods;
    }

    public static GoodsPo goods2GoodsPo(Goods goods) {
        if (goods == null) {
            return null;
        }

        GoodsPo goodsPo = new GoodsPo();

        goodsPo.setId(goods.getId());
        goodsPo.setGmtCreate(goods.getGmtCreate());
        goodsPo.setGmtModified(goods.getGmtModified());
        goodsPo.setName(goods.getName());
        goodsPo.setGoodsSn(goods.getGoodsSn());
        goodsPo.setShortName(goods.getShortName());
        goodsPo.setDescription(goods.getDescription());
        goodsPo.setBrief(goods.getBrief());
        goodsPo.setPicUrl(goods.getPicUrl());
        goodsPo.setDetail(goods.getDetail());
        goodsPo.setStatusCode(goods.getStatusCode());
        goodsPo.setShareUrl(goods.getShareUrl());
        goodsPo.setGallery(goods.getGallery());
        goodsPo.setGoodsCategoryId(goods.getGoodsCategoryId());
        goodsPo.setBrandId(goods.getBrandId());
        goodsPo.setBeDeleted(goods.getBeDeleted());
        goodsPo.setWeight(goods.getWeight());
        goodsPo.setVolume(goods.getVolume());
        goodsPo.setSpecialFreightId(goods.getSpecialFreightId());
        goodsPo.setBeSpecial(goods.getBeSpecial());
        goodsPo.setPrice(goods.getPrice());

        return goodsPo;
    }
}
