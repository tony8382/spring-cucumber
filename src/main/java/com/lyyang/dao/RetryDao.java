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

    public void testRetry2(int number) throws RuntimeException {
        log.info("GG2 retry");
        throw new RuntimeException("G2:" + number + ":" + RetrySynchronizationManager.getContext().getRetryCount());
    }
}
