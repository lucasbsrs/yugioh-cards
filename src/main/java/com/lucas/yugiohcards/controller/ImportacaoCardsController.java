package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
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
    public ResponseEntity<CartaMonstroDTO> importarTodasCartas() {
        CartaMonstroDTO cartaMonstroDTO = dbService.importacaoCartas("");

        return ResponseEntity.ok().body(cartaMonstroDTO);
    }

    @GetMapping("/card/{tipo}")
    public ResponseEntity<CartaMonstroDTO> importacaoCartas(@PathVariable("tipo") String tipo) {
        CartaMonstroDTO cartaMonstroDTO = dbService.importacaoCartas(tipo);

        return ResponseEntity.ok().body(cartaMonstroDTO);
    }

}
