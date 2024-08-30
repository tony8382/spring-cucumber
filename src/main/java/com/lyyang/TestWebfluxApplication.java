package com.lyyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class TestWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestWebfluxApplication.class, args);
	}

}
