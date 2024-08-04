package com.lyyang.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HelloController {
    @GetMapping("hello")
    public Mono<String> hello() {
        log.info("HGGGGH");
        ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .doOnNext(value -> log.info("GGGG: {}", value))
                .doOnError(error -> log.error("Error occurred: {}", error.getMessage()))
                .subscribe();
// Get the SecurityContext from the ReactiveSecurityContextHolder
        Mono<SecurityContext> context = ReactiveSecurityContextHolder.getContext();

        context.map(SecurityContext::getAuthentication)
                .subscribe(authentication -> System.out.println("Authentication object: " + authentication));

// Extract the authenticated user from the SecurityContext
        Mono<UserDetails> user = context.map(SecurityContext::getAuthentication)
                .map(authentication -> (UserDetails) authentication.getPrincipal());

// Print the username of the authenticated user
        user.map(UserDetails::getUsername)
                .subscribe(System.out::println);

        return Mono.just("welcome to reactiveDDD web");
    }

    @GetMapping("hello2")
    public String hello2() {
        log.info("HGGGGH");

        return "welcome to reactiveDDD2web";
    }

}
