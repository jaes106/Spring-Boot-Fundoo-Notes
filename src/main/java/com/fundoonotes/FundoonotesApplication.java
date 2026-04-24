package com.fundoonotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class FundooNotesApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundooNotesApplication.class, args);
    }
}
