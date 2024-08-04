package com.lyyang.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository users;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.just(this.users.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"))
        );
    }
}