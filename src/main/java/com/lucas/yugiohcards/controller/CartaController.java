package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.domains.CartaRecord;
import com.lucas.yugiohcards.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @GetMapping
    public ResponseEntity<List<CartaRecord>> buscarTodasCartas() {
        return ResponseEntity.ok(cartaService.buscarTodasCartas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaRecord> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cartaService.buscarPorId(id));
    }

}
