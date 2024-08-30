package com.lyyang.service;

import com.lyyang.dao.RetryDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetryService {

    private final RetryDao retryDao;

    public int testRetry() throws Exception {

        try {

            retryDao.testRetry();
            return 1;
        } catch (Exception e) {

            throw new Exception("GG");

        } finally {
            log.info("GG");

        }

    }
}
