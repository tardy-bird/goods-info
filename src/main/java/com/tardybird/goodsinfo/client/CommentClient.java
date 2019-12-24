package com.tardybird.goodsinfo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author nick
 */
@Component
@FeignClient("commentService")
public interface CommentClient {

    /**
     * 调用Comment模块删除评论
     * @param productId 产品ID
     * @return 是否成功
     */
    @DeleteMapping("/comments/{productId}")
    Boolean deleteComments(@PathVariable Integer productId);

}
