package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.config.RedisConfig;
import com.tardybird.goodsinfo.mapper.BrandMapper;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/6 19:39
 */

@Repository
public class GoodsDao {

    final GoodsMapper goodsMapper;

    final ProductMapper productMapper;

    final RedisTemplate<String, GoodsPo> redisTemplateOfGoods;

    final RedisTemplate<String, ProductPo> redisTemplateOfProducts;

    final RedisTemplate<String, List<String>> redisTemplateOfString;

    final RedisConfig redisConfig;

    final GoodsCategoryMapper goodsCategoryMapper;

    final BrandMapper brandMapper;

    public GoodsDao(GoodsMapper goodsMapper, ProductMapper productMapper,
                    RedisTemplate<String, GoodsPo> redisTemplateOfGoods,
                    RedisTemplate<String, ProductPo> redisTemplateOfProducts,
                    RedisTemplate<String, List<String>> redisTemplateOfString,
                    RedisConfig redisConfig, GoodsCategoryMapper goodsCategoryMapper,
                    BrandMapper brandMapper) {
        this.goodsMapper = goodsMapper;
        this.productMapper = productMapper;
        this.redisTemplateOfGoods = redisTemplateOfGoods;
        this.redisTemplateOfProducts = redisTemplateOfProducts;
        this.redisTemplateOfString = redisTemplateOfString;
        this.redisConfig = redisConfig;
        this.goodsCategoryMapper = goodsCategoryMapper;
        this.brandMapper = brandMapper;

        storeHotAndNewObjects();
    }


    /**
     * 返回一个goods的所有product
     *
     * @param id x
     * @return x
     */
    public List<ProductPo> getProductByGoodsId(Integer id) {
        // goods_id=1, key = Products_1
        String key = "Product_Ids_" + id;
        List<String> productIds = redisTemplateOfString.opsForValue().get(key);
        List<ProductPo> productList = new ArrayList<>();

        for (String productId : Objects.requireNonNull(productIds)) {
            String productKey = "Product_" + productId;
            ProductPo product = findProductById(Integer.valueOf(productId));
            productList.add(product);
        }
        return productList;

    }


    public ProductPo findProductById(Integer id) {
        String key = "Product_" + id;
        ProductPo product = redisTemplateOfProducts.opsForValue().get(key);
        if (product == null) {
            product = productMapper.getProductById(id);
            redisTemplateOfProducts.opsForValue().set(key, product);
        }
        return product;
    }

    /**
     * 通过id获得商品（包括他的product）
     *
     * @param id x
     * @return x
     */
    public GoodsPo getGoodsById(Integer id) {

        String key = "Goods_" + id;
        GoodsPo goodsObject = redisTemplateOfGoods.opsForValue().get(key);

        if (goodsObject == null) {
            goodsObject = goodsMapper.getGoodsById(id);
            redisTemplateOfGoods.opsForValue().set(key, goodsObject);
        }
        return goodsObject;
    }

    public void storeHotAndNewObjects() {

        List<GoodsPo> hotGoodsList = goodsMapper.findHotGoods();
        List<GoodsPo> newGoodsList = goodsMapper.findNewGoods();

        List<GoodsPo> cachedGoodsList = new ArrayList<>();

        cachedGoodsList.addAll(newGoodsList);
        cachedGoodsList.addAll(hotGoodsList);

        for (GoodsPo goods : cachedGoodsList) {
            String key = "Goods_" + goods.getId();

            redisTemplateOfGoods.opsForValue().set(key, goods);

            String idKey = "Product_Ids_" + goods.getId();


            // store productIds
//            List<Product> productList = productMapper.getProductByGoodsId(goods.getId());
//            for (Product product : productList) {
//                String ids = product.getProductIds();
//                // [1,2)
//                ids = ids.substring(1, ids.length() - 1);
//                String[] productIds = ids.split(",");
//
//                // store products
//                for (String productId : productIds) {
//                    Product cachedProduct = productMapper.getProductById(Integer.valueOf(productId));
//                    String productKey = "Product_" + productId;
//                    redisTemplateOfProducts.opsForValue().set(productKey, cachedProduct);
//                }
//
//                List<String> idList = new ArrayList<>();
//                Collections.addAll(idList, productIds);
//
//                redisTemplateOfString.opsForValue().set(idKey, idList);
//            }

        }

    }

}
