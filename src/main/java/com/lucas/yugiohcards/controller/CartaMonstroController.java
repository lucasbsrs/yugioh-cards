package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
import com.lucas.yugiohcards.model.CartaMonstro;
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
    public ResponseEntity<CartaMonstro> save(@Valid @RequestBody CartaMonstroDTO cartaMonstroDTO) {
        CartaMonstro cartaMonstro = model.map(cartaMonstroDTO, CartaMonstro.class);

        cartaMonstro = cartaMonstroService.save(cartaMonstro);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cartaMonstro.getId())
				.toUri();

		return ResponseEntity.created(location).build();
    }

    @GetMapping()
    public ResponseEntity<List<CartaMonstroDTO>> findAll() {
        List<CartaMonstroDTO> list = cartaMonstroService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaMonstro> update(@Valid @RequestBody CartaMonstroDTO cartaMonstroDTO, @PathVariable Long id) {
        CartaMonstro cartaMonstro = model.map(cartaMonstroDTO, CartaMonstro.class);
        cartaMonstro.setId(id);

        cartaMonstroService.save(cartaMonstro);

        return new ResponseEntity<CartaMonstro>(cartaMonstro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartaMonstroService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
