package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> getProductByGoodsId(Integer id) {
        return productMapper.getProductByGoodsId(id);
    }

    /**
     * 获取组合商品的其他商品
     *
     * @param id x
     * @return x
     */
    public List<Product> getOtherRelatedProducts(Integer id) {

        List<Product> productList = new ArrayList<>();

        String ids = productMapper.getProductById(id).getProductIds();
        // [1,2)
        ids = ids.substring(1, ids.length() - 1);
        String[] productIds = ids.split(",");
        for (String s : productIds) {
            Integer productId = Integer.valueOf(s);
            Product product = productMapper.getProductById(productId);
            productList.add(product);
        }
        return productList;
    }

    public void createProduct(Product product) {
        productMapper.createProduct(product);
    }


    public void updateProduct(Product product) {
        // TODO cannot actually update
        productMapper.updateProduct(product);
    }

    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }

    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }
}
