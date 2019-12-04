package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.domain.Goods;

import java.util.List;

/**
 * @author DIX
 * @date 2019/12/4 16:44
 */


public interface GoodsMapper {

    /**
     * 通过goodsSn查找商品
     * @param goodsSn
     * @return goods
     */
    Goods getGoodsByGoodsSn(String goodsSn);

    /***
     * 通过商品名字查找商品
     * @param name
     * @return goods
     */
    Goods getGoodsByName(String name);

    /**
     * 通过商品id查找商品
     * @param id
     * @return goods
     */
    Goods getGoodsById(Integer id);

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    int updateGoods(Goods goods);

    /**
     * 新建一个商品
     * @param goods
     * @return
     */
    int createGoods(Goods goods);


}
