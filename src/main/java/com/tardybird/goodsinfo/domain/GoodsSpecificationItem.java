package com.tardybird.goodsinfo.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author nick
 */
@Getter
@Setter
public class GoodsSpecificationItem implements Serializable {
    private String attribute;
    private String value;
}
