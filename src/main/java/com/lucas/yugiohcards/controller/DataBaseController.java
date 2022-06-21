package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database")
public class DataBaseController {

    @Autowired
    private DataBaseService dataBaseService;

	@PostMapping("/importar-cartas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> importarTodasCartas() throws Exception {
        dataBaseService.importarTodasCartas();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar-codigos")
    public ResponseEntity<?> atualizarCodigos() {

        dataBaseService.atualizarCodigosCartas();

        return ResponseEntity.noContent().build();
    }

}
