package com.lyyang.rest;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.security.RolesAllowed;

@RestController
public class TestController {
    @GetMapping("/admin")
    public Mono<String> admin() {
        return Mono.just("you are admin");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public Mono<String> user() {
        return Mono.just("you are user");
    }
}