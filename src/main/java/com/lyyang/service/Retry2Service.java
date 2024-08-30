package com.lyyang.service;

import com.lyyang.dao.RetryDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Retry2Service extends IRetry2Service {

    private final RetryDao retryDao;

    @Getter
    private final RetryTemplate retryTemplate;

    @Override
    public void doMethod(int number) throws RuntimeException {
        retryDao.testRetry2(number);
    }
}
