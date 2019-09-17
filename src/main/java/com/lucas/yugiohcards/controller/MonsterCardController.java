package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.dto.MonsterCardDTO;
import com.lucas.yugiohcards.model.MonsterCard;
import com.lucas.yugiohcards.service.MonsterCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/monstercard")
@CrossOrigin(origins = "*")
public class MonsterCardController {

    @Autowired
    private MonsterCardService monsterCardService;

    @PostMapping
    public ResponseEntity<MonsterCard> save(@RequestBody MonsterCardDTO monsterCardDTO) {
        MonsterCard monsterCard = monsterCardService.fromDTO(monsterCardDTO);

        monsterCardService.save(monsterCard);

        return new ResponseEntity<MonsterCard>(monsterCard, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MonsterCard>> findAll() {
        List<MonsterCard> list = monsterCardService.findAll();

        return new ResponseEntity<List<MonsterCard>>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonsterCard> update(@Valid @RequestBody MonsterCardDTO monsterCardDTO, @PathVariable Long id) {
        MonsterCard monsterCard = monsterCardService.fromDTO(monsterCardDTO);
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
