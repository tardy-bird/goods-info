package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.config.RedisConfig;
import com.tardybird.goodsinfo.controller.vo.GoodsVo;
import com.tardybird.goodsinfo.controller.vo.ProductVo;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.entity.Brand;
import com.tardybird.goodsinfo.entity.Product;
import com.tardybird.goodsinfo.entity.ShareRule;
import com.tardybird.goodsinfo.mapper.BrandMapper;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/6 19:39
 */

@Repository
public class GoodsDao {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisConfig redisConfig;

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    BrandMapper brandMapper;

    /**
     * 返回一个goods的所有product
     *
     * @param id x
     * @return x
     */
    public List<Product> getProductByGoodsId(Integer id) {
        return productMapper.getProductByGoodsId(id);
    }

    /**
     * 通过id获得商品（包括他的product）
     *
     * @param id x
     * @return x
     */
    public GoodsVo getGoodsById(Integer id) {
        String key = "GV_" + id;
//        Goods goods = (Goods) redisTemplate.opsForValue().get(key);
        GoodsVo goodsVo = (GoodsVo) redisTemplate.opsForValue().get(key);

        if (goodsVo == null) {
            Goods goods = goodsMapper.getGoodsById(id);
            GoodsCategory goodsCategory = goodsCategoryMapper.getCategory(goods.getGoodsCategoryId());
            Brand brand = brandMapper.getBrandById(goods.getBrandId());

            // TODO add actual rules
            List<ShareRule> shareRules = new ArrayList<>();

            // get ALL product VO
            List<ProductVo> products = new ArrayList<>();

            List<Product> productList = productMapper.getProductByGoodsId(id);

            // put ProductVo to productVoList

            List<ProductVo> productVoList = new ArrayList<>();

            for (Product product : productList) {

                ProductVo productVo = new ProductVo();

                productVo.setProduct(product);

                String productIds = product.getProductIds();
                // [1,2)
                productIds = productIds.substring(1, productIds.length() - 1);
                String[] ids = productIds.split(",");

                List<Product> productsTmp = new ArrayList<>();
                for (String s : ids) {
                    Product productTmp = productMapper.getProductById(Integer.valueOf(s));
                    productsTmp.add(productTmp);
                }

                productVo.setSubProducts(productsTmp);

                productVoList.add(productVo);

            }


            GoodsVo goodsVoTmp = new GoodsVo();

            goodsVoTmp.setShareRules(shareRules);
            goodsVoTmp.setProducts(products);
            goodsVoTmp.setBrand(brand);
            goodsVoTmp.setGoodsCategory(goodsCategory);

            goodsVoTmp.setProducts(productVoList);


            goodsVo = goodsVoTmp;

        }

        return goodsVo;
    }

    /**
     * 获得所有商品
     *
     * @return x
     */
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

}
