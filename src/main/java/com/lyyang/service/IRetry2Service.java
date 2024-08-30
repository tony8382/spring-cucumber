package com.lyyang.service;

import org.springframework.retry.support.RetryTemplate;

public abstract class IRetry2Service {
    void testRetry(int number) throws RuntimeException {
        getRetryTemplate().execute(context -> {
            doMethod(number);
            return null;
        });
    }

    abstract RetryTemplate getRetryTemplate();

    abstract void doMethod(int number) throws RuntimeException;
}
