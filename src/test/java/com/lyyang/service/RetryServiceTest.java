package com.lyyang.service;

import com.lyyang.TestWebfluxApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class RetryServiceTest extends TestWebfluxApplicationTests {

    @Autowired
    private RetryService retryService;

    @Test
    void testRetry() throws Exception {
        log.info("RR:{}", retryService.testRetry());
    }
}