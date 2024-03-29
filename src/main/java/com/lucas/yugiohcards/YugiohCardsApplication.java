package com.lucas.yugiohcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YugiohCardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(YugiohCardsApplication.class, args);
    }
}
