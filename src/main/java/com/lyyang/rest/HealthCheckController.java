package com.lyyang.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping(path = "/v1/status")
    public HttpStatus getV1Status() {
        return ResponseEntity.ok().build().getStatusCode();
    }

    @GetMapping(path = "/v2/status")
    public HttpStatus getV2Status() {
        return ResponseEntity.ok().build().getStatusCode();
    }
}