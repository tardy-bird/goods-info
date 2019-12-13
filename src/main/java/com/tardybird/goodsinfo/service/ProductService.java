package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.ProductPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> getProductByGoodsId(Integer id) {
        List<ProductPo> productPos = productMapper.getProductByGoodsId(id);
        List<Product> productList = new ArrayList<>();

        for (ProductPo productPo : productPos) {
            Product product = ObjectConversion.productPo2Product(productPo);

            //TODO set actual GoodsPo
            product.setGoodsPo(null);

            productList.add(product);
        }
        return productList;
    }

    public void createProduct(Product product) {
        ProductPo productPo = ObjectConversion.product2ProductPo(product);
        productMapper.createProduct(productPo);
    }


    public void updateProduct(Product product) {
        ProductPo productPo = ObjectConversion.product2ProductPo(product);
        // TODO cannot actually update
        productMapper.updateProduct(productPo);
    }

    public Product getProductById(Integer id) {
        ProductPo productPo = productMapper.getProductById(id);
        return ObjectConversion.productPo2Product(productPo);
    }

    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }
}
