package com.lyyang.rest;

import com.lyyang.exception.AAException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Service
public class IntervalHandler {

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ServerResponse> getTimes(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.interval(Duration.ofSeconds(1))
                        .map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }

    public Mono<ServerResponse> checkParameterRuntimeException(ServerRequest request) {
        try {

            request.queryParam("name").get();
            request.queryParam("G").get();
            return ok().contentType(MediaType.TEXT_PLAIN)
                    .body(Mono.just("Hello, " + request.queryParam("name").get()), String.class);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("EE"));
        }
    }

    public Mono<ServerResponse> checkParameterCustomException(ServerRequest request) {
        try {

            request.queryParam("name").get();
            request.queryParam("G").get();
            return ok().contentType(MediaType.TEXT_PLAIN)
                    .body(Mono.just("Hello, " + request.queryParam("name").get()), String.class);
        } catch (Exception e) {
            return Mono.error(new AAException("EEGG"));
        }
    }
}
