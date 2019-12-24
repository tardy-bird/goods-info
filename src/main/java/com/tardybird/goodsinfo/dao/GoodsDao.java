package com.tardybird.goodsinfo.dao;

import com.tardybird.goodsinfo.config.RedisConfig;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.po.ProductPo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author nick
 */
@Repository
public class GoodsDao {

    final RedisConfig redisConfig;
    final ProductMapper productMapper;
    final GoodsMapper goodsMapper;
    final RedisTemplate<String, GoodsPo> redisTemplateOfGoods;
    final RedisTemplate<String, ProductPo> redisTemplateOfProducts;
    final RedisTemplate<String, List<String>> redisTemplateOfString;


    public GoodsDao(RedisConfig redisConfig,
                    RedisTemplate<String, GoodsPo> redisTemplateOfGoods,
                    RedisTemplate<String, ProductPo> redisTemplateOfProducts,
                    RedisTemplate<String, List<String>> redisTemplateOfString,
                    ProductMapper productMapper,
                    GoodsMapper goodsMapper) {
        this.redisConfig = redisConfig;
        this.redisTemplateOfGoods = redisTemplateOfGoods;
        this.redisTemplateOfProducts = redisTemplateOfProducts;
        this.redisTemplateOfString = redisTemplateOfString;
        this.productMapper = productMapper;
        this.goodsMapper = goodsMapper;
    }

    /**
     * 通过商品ID获取产品
     *
     * @param id 商品ID
     * @return 产品列表
     */
    public List<ProductPo> getProductByGoodsId(Integer id) {

        // 每个商品对应的产品ID列表
        String key = "Goods_Product_" + id;
        List<String> productIds = redisTemplateOfString.opsForValue().get(key);

        // 不存在这样的列表
        if (productIds == null) {

            // 到数据库中查询
            List<Integer> productIdsByGoodsId = productMapper.findProductIdsByGoodsId(id);

            productIds = new ArrayList<>();
            for (Integer productId : productIdsByGoodsId) {
                productIds.add(productId.toString());
            }

            // 将ID列表缓存到Redis中
            redisTemplateOfString.opsForValue().set(key, productIds, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
        }

        List<ProductPo> productPoList = new ArrayList<>();

        for (String productId : productIds) {

            // 检查产品信息是否有缓存
            String productKey = "Product_" + productId;
            ProductPo productPo = redisTemplateOfProducts.opsForValue().get(productKey);

            // 到数据库中拿
            if (productPo == null) {
                productPo = productMapper.getProductById(Integer.valueOf(productId));
            }
            productPoList.add(productPo);
        }
        return productPoList;
    }

    /**
     * 通过商品种类拿商品信息
     *
     * @param id    种类
     * @param page  页数
     * @param limit 每页大小
     * @return 商品信息
     */
    public List<GoodsPo> findGoodsByCategoryId(Integer id, Integer page, Integer limit) {

        // 种类ID对应一个商品ID列表
        String key = "Category_Goods_" + id;
        List<String> goodsIds = redisTemplateOfString.opsForValue().get(key);

        if (goodsIds == null) {
            List<Integer> goodsIdsByCategoryId = goodsMapper.findGoodsIdsByCategoryId(id, page, limit);

            if (goodsIdsByCategoryId == null) {
                return null;
            }

            goodsIds = new ArrayList<>();

            for (Integer goodsId : goodsIdsByCategoryId) {
                goodsIds.add(String.valueOf(goodsId));
            }

            redisTemplateOfString.opsForValue().set(key, goodsIds, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
        }

        // 将查询到的商品装进列表返回
        List<GoodsPo> goodsPos = new ArrayList<>();
        for (String goodsId : goodsIds) {

            String goodsKey = "Goods_" + goodsId;
            GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(goodsKey);
            if (goodsPo == null) {
                goodsPo = goodsMapper.getGoodsByIdUser(Integer.valueOf(goodsId));

                redisTemplateOfGoods.opsForValue().set(goodsKey, goodsPo, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);

            }

            goodsPos.add(goodsPo);
        }

        return goodsPos;
    }

    /**
     * 根据ID获取产品信息
     *
     * @param id 产品
     * @return 产品信息
     */
    public ProductPo findProductById(Integer id) {
        String key = "Product_" + id;
        ProductPo product = redisTemplateOfProducts.opsForValue().get(key);
        if (product == null) {

            product = productMapper.getProductById(id);
            redisTemplateOfProducts.opsForValue().set(key, product, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);

        }

        return product;
    }

    /**
     * 通过id获得商品(包括product)
     *
     * @param id 商品ID
     * @return 商品信息
     */
    public GoodsPo getGoodsByIdAdmin(Integer id) {

        String key = "Goods_" + id;
        GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(key);

        if (goodsPo == null) {
            goodsPo = goodsMapper.getGoodsByIdAdmin(id);
            redisTemplateOfGoods.opsForValue().set(key, goodsPo, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
        }

        return goodsPo;
    }

    /**
     * 用户获取商品信息(不能看到未上架的)
     *
     * @param id 商品ID
     * @return 商品信息
     */
    public GoodsPo getGoodsByIdUser(Integer id) {

        String key = "Goods_" + id;
        GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(key);

        if (goodsPo == null) {
            goodsPo = goodsMapper.getGoodsByIdUser(id);
            redisTemplateOfGoods.opsForValue().set(key, goodsPo, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
        }

        return goodsPo;
    }

    /**
     * 预先将热的新的商品存到Redis中
     */
    @PostConstruct
    public void storeHotAndNewObjects() {

        // 从数据库中拿到
        List<GoodsPo> cachedGoodsList = goodsMapper.findNewAndHotGoods();

        for (GoodsPo goods : cachedGoodsList) {

            String key = "Goods_" + goods.getId();
            GoodsPo goodsPo = redisTemplateOfGoods.opsForValue().get(key);

            if (goodsPo == null) {
                redisTemplateOfGoods.opsForValue().set(key, goods, redisConfig.getRedisExpireTime(), TimeUnit.MINUTES);
            }

        }
    }

}
