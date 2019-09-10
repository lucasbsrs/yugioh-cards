package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.model.MonsterCard;
import com.lucas.yugiohcards.service.MonsterCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monstercard")
@CrossOrigin(origins = "*")
public class MonsterCardController {

    @Autowired
    private MonsterCardService monsterCardService;

    @GetMapping()
    public ResponseEntity<List<MonsterCard>> findAll() {
        List<MonsterCard> list = monsterCardService.findAll();

        //return ResponseEntity.ok().body(list);
        return new ResponseEntity<List<MonsterCard>>(list, HttpStatus.OK);
    }

}
