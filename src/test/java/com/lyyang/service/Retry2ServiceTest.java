package com.lyyang.service;

import com.lyyang.TestWebfluxApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class Retry2ServiceTest extends TestWebfluxApplicationTests {

    @Autowired
    private Retry2Service retry2Service;

    @Autowired
    private RetryService retryService;

    @Test
    void testRetry() throws Exception {
        try {
            retry2Service.testRetry(1);
        } catch (Exception e) {
            log.info("ERR", e);
        }

        try {
            retry2Service.testRetry(2);
        } catch (Exception e) {
            log.info("ERR", e);
        }
//        try {
//            log.info("RR:{}", retryService.testRetry());
//        }catch (Exception e){
//
//        }

    }
}