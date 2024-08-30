package com.lyyang.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryDao {

    @Retryable(include = {RuntimeException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public void testRetry() throws RuntimeException {
        log.info("GG retry");
        throw new RuntimeException("GG");
    }
}
