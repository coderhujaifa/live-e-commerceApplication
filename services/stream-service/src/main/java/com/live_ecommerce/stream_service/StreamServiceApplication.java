package com.live_ecommerce.stream_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication
@Configuration
@EnableWebSecurity
public class StreamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamServiceApplication.class, args);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// ✅ New style for disabling CSRF
				.csrf(csrf -> csrf.disable())

				// ✅ Allow all requests (no auth)
				.authorizeHttpRequests(auth -> auth
						.anyRequest().permitAll()
				);

		return http.build();
	}
}
