package com.lucas.yugiohcards.integrations.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "ygopro", url = "${integration.ygopro.url}", configuration = IntegrationConfiguration.class)
public interface YgoProClient {

//    @GetMapping
//    List<CartaMonstroDTO> buscarTodosCards();

    @GetMapping
    List<Object[]> buscarTodosCards();

}
