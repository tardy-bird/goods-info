package com.tardybird.goodsinfo.domain;

import com.tardybird.goodsinfo.po.ProductPo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 产品对象
 *
 * @author 数据库与对象模型标准组
 * @date Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Product extends ProductPo {
    private Goods goods;
}
