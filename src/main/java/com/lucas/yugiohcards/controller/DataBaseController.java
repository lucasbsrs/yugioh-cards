package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.service.DataBaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/database")
public class DataBaseController {

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

    @PutMapping("/atualizar-nomes-cartas")
    public ResponseEntity<?> atualizarNomeCartas() {

        dataBaseService.atualizarNomeCartas();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar-novas-cartas")
    public ResponseEntity<?> atualizarNovasCartas() {

        dataBaseService.atualizarNovasCartas();

        return ResponseEntity.noContent().build();
    }

}
