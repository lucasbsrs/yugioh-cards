package com.lucas.yugiohcards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.yugiohcards.model.MonsterCard;
import com.lucas.yugiohcards.repository.MonsterCardRepository;

@Service
public class MonsterCardService {

    @Autowired
    private MonsterCardRepository monsterCardRepository;

    public List<MonsterCard> findAll() {
        return monsterCardRepository.findAll();
    }

    public MonsterCard save(MonsterCard monsterCard){
        return monsterCardRepository.save(monsterCard);
    }

    public void delete(Long id){
        monsterCardRepository.deleteById(id);
    }
    
}
