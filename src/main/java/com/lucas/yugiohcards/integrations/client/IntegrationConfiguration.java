package com.lucas.yugiohcards.integrations.client;


import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;


public class IntegrationConfiguration {
    @Bean
    public Retryer retryer() {
        return new CustomRetryer(2000, Integer.valueOf(5));
    }

    @Bean
    public ErrorDecoder myErrorDecoder() {
        return new MyErrorDecoder();
    }
}
