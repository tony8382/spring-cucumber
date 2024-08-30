package com.lyyang.service;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

public class MyRetryListener implements RetryListener {
    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        context.setAttribute("firstException", null);
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        Throwable firstException = (Throwable) context.getAttribute("firstException");
        if (context.getRetryCount() > 0 && firstException != null) {
            throw new RuntimeException("Retry attempts exhausted. First error was: " + firstException.getMessage(), firstException);
        }
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        if (context.getAttribute("firstException") == null) {
            context.setAttribute("firstException", throwable);
        }
    }
}
