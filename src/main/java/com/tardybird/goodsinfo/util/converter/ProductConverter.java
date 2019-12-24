package com.tardybird.goodsinfo.util.converter;

import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.po.ProductPo;

import java.util.function.Function;

/**
 * @author nick
 */
public class ProductConverter extends Converter<ProductPo, Product> {

    public ProductConverter() {
        super(new ProductPoFunction(), new ProductFunction());
    }

    static class ProductPoFunction implements Function<ProductPo,Product> {

        @Override
        public Product apply(ProductPo productPo) {
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
    }

    static class ProductFunction implements Function<Product,ProductPo> {

        @Override
        public ProductPo apply(Product product) {
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
    }
}
