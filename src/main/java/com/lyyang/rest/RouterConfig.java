package com.lyyang.rest;

import com.lyyang.filter.ExampleHandlerFilterFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Autowired
    private FunctionHandler functionHandler;

    @Autowired
    private IntervalHandler intervalHandler;

    @Autowired
    private ExampleHandlerFilterFunction exampleHandlerFilterFunction;

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("fhello2"), functionHandler::getTime).filter(exampleHandlerFilterFunction);
    }

    @Bean
    public RouterFunction<ServerResponse> intervalRouter() {
        return route(GET("hello/{test}"), intervalHandler::getTimes)
                .andRoute(GET("fhello4"), intervalHandler::checkParameterRuntimeException)
                .andRoute(GET("fhello5"), intervalHandler::checkParameterCustomException);
    }
}
