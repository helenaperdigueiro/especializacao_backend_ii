package com.dh.cryptocurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class CryptocurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptocurrencyApplication.class, args);
    }

}
