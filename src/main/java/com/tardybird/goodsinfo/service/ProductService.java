package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    final
    ProductMapper productMapper;
    final
    GoodsDao goodsDao;
    final GoodsMapper goodsMapper;

    public ProductService(ProductMapper productMapper, GoodsDao goodsDao, GoodsMapper goodsMapper) {
        this.productMapper = productMapper;
        this.goodsDao = goodsDao;
        this.goodsMapper = goodsMapper;
    }

    public List<Product> getProductByGoodsId(Integer id) {

        List<ProductPo> productPos = goodsDao.getProductByGoodsId(id);
        List<Product> productList = new ArrayList<>();

        for (ProductPo productPo : productPos) {
            Product product = ObjectConversion.productPo2Product(productPo);

            GoodsPo goodsPos = goodsDao.getGoodsByIdAdmin(product.getGoodsId());
            product.setGoodsPo(goodsPos);

            productList.add(product);
        }
        return productList;
    }

    public Boolean createProduct(Product product) {
        ProductPo productPo = ObjectConversion.product2ProductPo(product);
        Integer affectedRows = productMapper.createProduct(productPo);
        return affectedRows > 0;
    }


    public Boolean updateProduct(Product product) {
        ProductPo productPo = ObjectConversion.product2ProductPo(product);
        Integer affectedRows = productMapper.updateProduct(productPo);
        return affectedRows > 0;
    }

    public Product getProductById(Integer id) {

        ProductPo productPo = goodsDao.findProductById(id);

        if (productPo == null) {
            return null;
        }

        Product product = ObjectConversion.productPo2Product(productPo);
        GoodsPo goodsPo = goodsDao.getGoodsByIdAdmin(productPo.getGoodsId());
        product.setGoodsPo(goodsPo);

        return product;
    }

    public Boolean deleteProduct(Integer id) {
        Integer affectedRows = productMapper.deleteProduct(id);
        return affectedRows > 0;
    }
}
