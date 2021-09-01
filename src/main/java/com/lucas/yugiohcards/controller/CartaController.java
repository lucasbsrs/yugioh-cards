package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.service.CartaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

	private ModelMapper model = new ModelMapper();

	@PostMapping("/importar-cartas")
    public ResponseEntity<?> importarTodasCartas() throws Exception {
        ImportacaoCartaResponse cartas = cartaService.importarTodasCartas();

        return ResponseEntity.ok().build();
    }

}