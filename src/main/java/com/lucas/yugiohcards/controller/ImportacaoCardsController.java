package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.integrations.dto.ImportacaoCartaYgoProDTO;
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
    public ResponseEntity<ImportacaoCartaYgoProDTO> importarTodasCartas() {
        dbService.importarTodoasCartas();

        return ResponseEntity.ok().build();
    }

//    @GetMapping("/card/{tipo}")
//    public ResponseEntity<ImportacaoCartaYgoProDTO> importacaoCartas(@PathVariable("tipo") String tipo) {
//        ImportacaoCartaYgoProDTO importacaoCartaYgoProDTO = dbService.importacaoCartas(tipo);
//
//        return ResponseEntity.ok().body(importacaoCartaYgoProDTO);
//    }

}
