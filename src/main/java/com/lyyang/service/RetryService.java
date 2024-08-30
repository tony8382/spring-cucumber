package com.lyyang.service;

import com.lyyang.dao.RetryDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetryService {

    private final @NotNull RetryDao retryDao;
    private final @NotNull RetryTemplate retryTemplate;

    public int testRetry() throws Exception {
        int i = 1;
        try {

            retryTemplate.execute(context -> {
                retryDao.testRetry();
                return null;
            });

            i = 2;
            return 1;
        } catch (Exception e) {
            i = 3;
            log.info("ee", e);
            return i;

        } finally {
            i = 4;
            log.info("GG");
        }

    }
}
