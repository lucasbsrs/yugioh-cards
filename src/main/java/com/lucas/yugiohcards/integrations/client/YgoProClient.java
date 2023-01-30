package com.lucas.yugiohcards.integrations.client;

import com.lucas.yugiohcards.integrations.response.ChangeLogIdResponse;
import com.lucas.yugiohcards.integrations.response.ChangeLogNameResponse;
import com.lucas.yugiohcards.integrations.response.CheckDatabaseVersionResponse;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "ygopro", url = "${ygopro.url}")
public interface YgoProClient {

    @GetMapping("/cardinfo.php")
    ImportacaoCartaResponse buscarTodasCartas();

    @GetMapping("/changelogID.php")
    ChangeLogIdResponse consultarChangeLogId();

    @GetMapping("/changelogName.php")
    ChangeLogNameResponse consultarChangeLogName();

    @GetMapping("/checkDBVer.php")
    List<CheckDatabaseVersionResponse> consultarVersaoDataBase();

}
