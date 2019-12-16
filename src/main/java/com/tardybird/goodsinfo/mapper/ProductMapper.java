package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.po.ProductPo;
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
    List<ProductPo> getProductByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 查看单个货品信息
     *
     * @param id x
     * @return x
     */
    ProductPo getProductById(@Param("id") Integer id);

    List<Integer> findProductIdsByGoodsId(@Param("id") Integer id);

    /**
     * 新建一个货品
     *
     * @param product z
     * @return z
     */
    Integer createProduct(ProductPo product);

    /**
     * 更新一个货品信息
     *
     * @param product x
     * @return x
     */
    Integer updateProduct(ProductPo product);

    /**
     * 删除一个商品
     *
     * @param id x
     * @return x
     */
    Integer deleteProduct(@Param("id") Integer id);

    Integer updateProductByGoodsId(@Param("id") Integer id);

    Integer deductQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);
}
