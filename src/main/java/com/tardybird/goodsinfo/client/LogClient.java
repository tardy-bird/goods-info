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
     * x
     * @param log x
     */
    @PostMapping("/log")
    void addLog(@RequestBody Log log);
}
