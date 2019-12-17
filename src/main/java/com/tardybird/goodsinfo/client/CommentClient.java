package com.tardybird.goodsinfo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author nick
 */
@Component
@FeignClient("Comment")
public interface CommentClient {

    /**
     * x
     * @param productId x
     * @return x
     */
    @DeleteMapping("/comments/{productId}")
    Boolean deleteComments(@PathVariable Integer productId);

}
