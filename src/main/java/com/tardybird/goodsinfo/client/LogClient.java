package com.tardybird.goodsinfo.client;

import com.tardybird.goodsinfo.domain.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author nick
 */
@Component
@FeignClient(name = "logService")
public interface LogClient {
    /**
     * 调用Log模块
     * @param log Log
     */
    @PostMapping("/log")
    void addLog(@RequestBody Log log);
}
