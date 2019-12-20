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
}
