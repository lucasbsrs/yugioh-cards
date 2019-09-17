package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.dto.MonsterCardDTO;
import com.lucas.yugiohcards.model.MonsterCard;
import com.lucas.yugiohcards.service.MonsterCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
