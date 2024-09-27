package com.example.JsonPlaceholder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class JsonPlaceholderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonPlaceholderApplication.class, args);
	}

}
