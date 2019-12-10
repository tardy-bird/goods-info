package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.entity.Product;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> getProductByGoodsId(Integer id) {
        return productMapper.getProductByGoodsId(id);
    }

    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }
}
