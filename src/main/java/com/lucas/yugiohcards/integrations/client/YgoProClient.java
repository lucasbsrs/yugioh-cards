package com.lucas.yugiohcards.integrations.client;

import com.lucas.yugiohcards.integrations.response.ChangeLogResponse;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ygopro", url = "${ygopro.url}")
public interface YgoProClient {

    @GetMapping("/cardinfo.php")
    ImportacaoCartaResponse buscarTodasCartas();

    @GetMapping("/changelogID.php")
    ChangeLogResponse consultarChangeLogId();

}
