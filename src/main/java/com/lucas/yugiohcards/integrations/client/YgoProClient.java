package com.lucas.yugiohcards.integrations.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ygopro", url = "${integration.ygopro.url}", configuration = IntegrationConfiguration.class)//,  fallbackFactory = TaxaCambioFalbackFactory.class)
//@RequestMapping()
public interface TaxaCambioClient {

    @GetMapping(value = "/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?$format=json&@moeda='{moeda}'&@dataCotacao='{dataCotacao}'")
    CotacaoPtaxDTO buscaCotacaoMoedaDia(@PathVariable(value = "moeda") String moeda,
                                        @PathVariable(value = "dataCotacao") String dataCotacao);

}
