package com.tardybird.goodsinfo.po;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分享规则
 *
 * @author 数据库与对象模型标准组
 * @date Created in 16:00 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShareRulePo implements Serializable {
    private Integer id;
    /**
     * 分享等级（分享次数所对应返利）
     * JSON格式: {"strategy": [{"lowerbound":xxx, "upperbound":xxx, "rate":xxx}]}, xxx为具体数值
     */
    private String shareLevelStrategy;
    /**
     * 商品ID
     */
    private Integer goodsId;

    private Boolean beDeleted;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
