package com.digital.house.aula03_integracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Aula03IntegracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Aula03IntegracaoApplication.class, args);
	}

	@Configuration
	public class SecurityConfig {

		@Bean
		public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

			http
					.authorizeExchange()
					.anyExchange()
					.authenticated()
					.and()
					.oauth2Login();

			return http.build();
		}

	}

	@RestController
	public class Controller {

		@GetMapping("check")
		public ResponseEntity<String> status() { return ResponseEntity.ok("Usuário autenticado"); }
	}

}
