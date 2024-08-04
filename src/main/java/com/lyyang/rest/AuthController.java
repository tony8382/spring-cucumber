package com.lyyang.rest;


import com.lyyang.jwt.JwtTokenProvider;
import com.lyyang.model.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final ReactiveAuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public Mono<String> login(@RequestBody AuthRequest request) {
        String username = request.getUsername();
        Mono<Authentication> authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));

        return authentication.map(auth -> jwtTokenProvider.createToken(auth));
    }
}