package com.tardybird.goodsinfo.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nick
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandVo {
    private String name;
    private String description;
    private MultipartFile picture;
}
