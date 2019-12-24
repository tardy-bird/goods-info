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
     * @param id 货品ID
     * @return 货品
     */
    ProductPo getProductById(@Param("id") Integer id);

    /**
     * 管理员查询商品下的产品
     *
     * @param id 商品ID
     * @return 产品
     */
    List<Integer> findProductIdsByGoodsId(@Param("id") Integer id);

    /**
     * 新建一个货品
     *
     * @param product 货品
     * @return 改变行数
     */
    Integer createProduct(ProductPo product);

    /**
     * 更新一个货品信息
     *
     * @param product 货品
     * @return 改变行数
     */
    Integer updateProduct(ProductPo product);

    /**
     * 删除一个商品
     *
     * @param id 商品ID
     * @return 改变行数
     */
    Integer deleteProduct(@Param("id") Integer id);

    /**
     * 通过商品ID删除货品
     * @param id 商品ID
     * @return 改变行数
     */
    Integer updateProductByGoodsId(@Param("id") Integer id);
}
