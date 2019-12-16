package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.dao.ProductDao;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.data.redis.core.RedisTemplate;
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
    final ProductDao productDao;
    final RedisTemplate<String, ProductPo> redisTemplateOfProduct;

    public ProductService(ProductMapper productMapper, GoodsDao goodsDao, GoodsMapper goodsMapper, ProductDao productDao, RedisTemplate<String, ProductPo> redisTemplateOfProduct) {
        this.productMapper = productMapper;
        this.goodsDao = goodsDao;
        this.goodsMapper = goodsMapper;
        this.productDao = productDao;
        this.redisTemplateOfProduct = redisTemplateOfProduct;
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
        if (product == null) {
            return false;
        }

        ProductPo productPo = ObjectConversion.product2ProductPo(product);
        Integer affectedRows = productMapper.updateProduct(productPo);
        Boolean status = affectedRows > 0;

        if (status) {
            String productKey = "Product_" + product.getId();
            ProductPo cachedProductPo = redisTemplateOfProduct.opsForValue().get(productKey);
            if (cachedProductPo != null) {
                redisTemplateOfProduct.delete(productKey);
                redisTemplateOfProduct.opsForValue().set(productKey, productPo);
            }
        }
        return status;
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
        Boolean status = affectedRows > 0;
        if (status) {
            String productKey = "Product_" + id;
            ProductPo productPo = redisTemplateOfProduct.opsForValue().get(productKey);
            if (productPo != null) {
                redisTemplateOfProduct.delete(productKey);
            }
        }
        return status;
    }


    public Boolean deductGoodsSafetyStock(Integer id, Integer quantity) {
        return productDao.deductProductStock(id, quantity);
    }
}
