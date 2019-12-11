package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DIX
 * @date 2019/12/4 17:09
 */
@Repository
public interface ProductMapper {

    /**
     * 获得一个商品的所有对应货品
     *
     * @param goodsId x
     * @return x
     */
    List<Product> getProductByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 查看单个货品信息
     *
     * @param id x
     * @return x
     */
    Product getProductById(@Param("id") Integer id);

    /**
     * 新建一个货品
     *
     * @param product z
     * @return z
     */
    int createProduct(Product product);

    /**
     * 更新一个货品信息
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 删除一个商品
     *
     * @param id
     * @return
     */
    int deleteProduct(@Param("id") Integer id);
}
