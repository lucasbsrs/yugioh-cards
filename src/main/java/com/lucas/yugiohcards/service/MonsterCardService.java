package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.model.MonsterCard;
import com.lucas.yugiohcards.repository.MonsterCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterCardService {

    @Autowired
    private MonsterCardRepository monsterCardRepository;

    public List<MonsterCard> findAll() {
        return monsterCardRepository.findAll();
    }
}
