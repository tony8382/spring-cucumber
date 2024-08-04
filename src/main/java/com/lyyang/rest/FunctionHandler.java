package com.lyyang.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@Slf4j
public class FunctionHandler {
    public Mono<ServerResponse> getTime(ServerRequest serverRequest) {

        log.info("HIHI for getTime");
        return ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Now is HIHI"), String.class);
    }
}
