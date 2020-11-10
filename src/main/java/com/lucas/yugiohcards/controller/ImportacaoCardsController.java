package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.dto.ImportacaoCartaDTO;
import com.lucas.yugiohcards.service.DBService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/importacao")
public class ImportacaoCardsController {

    @Autowired
    private DBService dbService;

	private ModelMapper model = new ModelMapper();

    @GetMapping("/card")
    public ResponseEntity<ImportacaoCartaDTO> importarTodasCartas() {
        ImportacaoCartaDTO importacaoCartaDTO = dbService.importacaoCartas("");

        return ResponseEntity.ok().body(importacaoCartaDTO);
    }

    @GetMapping("/card/{tipo}")
    public ResponseEntity<ImportacaoCartaDTO> importacaoCartas(@PathVariable("tipo") String tipo) {
        ImportacaoCartaDTO importacaoCartaDTO = dbService.importacaoCartas(tipo);

        return ResponseEntity.ok().body(importacaoCartaDTO);
    }

}
