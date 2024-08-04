package com.lyyang.exception.handler;

import com.lyyang.exception.AAException;
import com.lyyang.exception.enums.ErrorAttributesKey;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    public static final Map<Class<?>, HttpStatus> exceptionsRules;

    static {
        exceptionsRules = new HashMap<>();
        exceptionsRules.put(AAException.class, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = getError(request);

        final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        Map<String, Object> errorAttrs = new HashMap<>();
        if (exceptionsRules.containsKey(error.getClass())) {
            errorAttrs.put(ErrorAttributesKey.STATUS.getKey(), exceptionsRules.get(error.getClass()).value());
        } else {
            Map<String, Object> parentErrorAttrs = super.getErrorAttributes(request, options);
            errorAttrs.put(ErrorAttributesKey.STATUS.getKey(), parentErrorAttrs.get(ErrorAttributesKey.STATUS.getKey()));
        }
        errorAttrs.put(ErrorAttributesKey.MESSAGE.getKey(), error.getMessage());
        errorAttrs.put(ErrorAttributesKey.TIME.getKey(), timestamp);
        return errorAttrs;
    }


}