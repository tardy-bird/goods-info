package com.tardybird.goodsinfo.po;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品种类的信息
 * @author 数据库与对象模型标准组
 * @date Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GoodsCategoryPo implements Serializable {
    private Integer id;
    /**
     * 种类的名称
     */
    private String name;
    /**
     * 该种类的父种类ID
     */
    private Integer pid;
    /**
     * 二级目录的pic
     */
    private String picUrl;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

}
