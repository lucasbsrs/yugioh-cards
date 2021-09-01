package com.lucas.yugiohcards.integrations.client;

import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "importador-cartas", url = "${importador-cartas.url}")
public interface ImportadorCartasClient {

    @GetMapping("/card")
    ImportacaoCartaResponse buscarTodasCartas();

}
