package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.service.CartaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database")
public class DataBaseController {

    @Autowired
    private CartaService cartaService;

    private ModelMapper modelMapper = new ModelMapper();

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
