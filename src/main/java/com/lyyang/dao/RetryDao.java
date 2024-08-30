package com.lyyang.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetryDao {

    public void testRetry() throws RuntimeException {
        log.info("GG retry");
        throw new RuntimeException("GG" + RetrySynchronizationManager.getContext().getRetryCount());
    }
}
