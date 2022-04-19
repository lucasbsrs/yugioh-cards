package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

	@PostMapping("/importar-cartas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> importarTodasCartas() throws Exception {
        cartaService.importarTodasCartas();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar-codigos")
    public ResponseEntity<?> atualizarCodigos() {

        cartaService.atualizarCodigosCartas();

        return ResponseEntity.noContent().build();
    }

}
