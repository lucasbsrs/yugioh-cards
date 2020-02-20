package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.dto.MonsterCardDTO;
import com.lucas.yugiohcards.model.MonsterCard;
import com.lucas.yugiohcards.service.MonsterCardService;

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
public class MonsterCardController {

    @Autowired
    private MonsterCardService monsterCardService;

	private ModelMapper model = new ModelMapper();
    
    @PostMapping
    public ResponseEntity<MonsterCard> save(@Valid @RequestBody MonsterCardDTO monsterCardDTO) {
        MonsterCard monsterCard = model.map(monsterCardDTO, MonsterCard.class);

        monsterCard = monsterCardService.save(monsterCard);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(monsterCard.getId())
				.toUri();

		return ResponseEntity.created(location).build();
    }

    @GetMapping()
    public ResponseEntity<List<MonsterCard>> findAll() {
        List<MonsterCard> list = monsterCardService.findAll();

        return new ResponseEntity<List<MonsterCard>>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonsterCard> update(@Valid @RequestBody MonsterCardDTO monsterCardDTO, @PathVariable Long id) {
        MonsterCard monsterCard = model.map(monsterCardDTO, MonsterCard.class);
        monsterCard.setId(id);

        monsterCardService.save(monsterCard);

        return new ResponseEntity<MonsterCard>(monsterCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        monsterCardService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
