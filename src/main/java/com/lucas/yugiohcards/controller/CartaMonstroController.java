package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
import com.lucas.yugiohcards.integrations.dto.ImportacaoCartaYgoProDTO;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.service.CartaMonstroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/monsterCards")
public class CartaMonstroController {

    @Autowired
    private CartaMonstroService cartaMonstroService;

	private ModelMapper model = new ModelMapper();
    
    @PostMapping
    public ResponseEntity<Carta> save(@Valid @RequestBody ImportacaoCartaYgoProDTO importacaoCartaYgoProDTO) {
        Carta carta = model.map(importacaoCartaYgoProDTO, Carta.class);

        carta = cartaMonstroService.save(carta);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(carta.getId())
				.toUri();

		return ResponseEntity.created(location).build();
    }

    @GetMapping()
    public ResponseEntity<List<CartaMonstroDTO>> findAll() {
        List<CartaMonstroDTO> list = cartaMonstroService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{nomeCarta}")
    public ResponseEntity<CartaMonstroDTO> buscarCartaPorNome(@PathVariable String nomeCarta) {
        CartaMonstroDTO list = cartaMonstroService.buscarCartaPorNome(nomeCarta);

        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carta> update(@Valid @RequestBody ImportacaoCartaYgoProDTO importacaoCartaYgoProDTO, @PathVariable Long id) {
        Carta carta = model.map(importacaoCartaYgoProDTO, Carta.class);
        carta.setId(id);

        cartaMonstroService.save(carta);

        return new ResponseEntity<Carta>(carta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartaMonstroService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
