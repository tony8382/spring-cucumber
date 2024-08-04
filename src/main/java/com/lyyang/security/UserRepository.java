package com.lyyang.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final Map<String, UserDetails> allUsers = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;


    //ref this to mongo: https://matthung0807.blogspot.com/2019/10/spring-security-webflux.html
    @PostConstruct
    protected void init() {
        allUsers.put("pkslow", new UserDetailsImpl("pkslow", passwordEncoder.encode("123456"), new SimpleGrantedAuthority("ROLE_ADMIN")));
        allUsers.put("user", new UserDetailsImpl("user", passwordEncoder.encode("123456"), new SimpleGrantedAuthority("ROLE_USER")));
    }

    public Optional<UserDetails> findByUsername(String username) {
        return Optional.ofNullable(allUsers.get(username));
    }
}