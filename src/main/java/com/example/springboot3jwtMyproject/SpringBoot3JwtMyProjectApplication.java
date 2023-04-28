package com.example.springboot3jwtMyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBoot3JwtMyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3JwtMyProjectApplication.class, args);
	}

}


