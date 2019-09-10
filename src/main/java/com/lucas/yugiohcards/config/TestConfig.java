package com.lucas.yugiohcards.config;

import com.lucas.yugiohcards.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    public DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws Exception {
        dbService.instantiateTestDatabase();
        return true;
    }
}
