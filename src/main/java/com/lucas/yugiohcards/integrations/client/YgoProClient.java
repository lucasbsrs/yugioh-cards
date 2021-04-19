package com.lucas.yugiohcards.integrations.client;

import com.lucas.yugiohcards.integrations.dto.ImportacaoCartaYgoProDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ygopro", url = "${integration.ygopro.url}", configuration = IntegrationConfiguration.class)
public interface YgoProClient {

    @GetMapping
    ImportacaoCartaYgoProDTO buscarTodosCards();

}
