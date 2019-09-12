package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.builder.MonsterCardBuilder;
import com.lucas.yugiohcards.dto.MonsterCardDTO;
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

    public MonsterCard fromDTO(MonsterCardDTO monsterCardDTO) {
        MonsterCard monsterCard = MonsterCardBuilder.builder()
                .name(monsterCardDTO.getName())
                .description(monsterCardDTO.getDescription())
                .get();
        return null;
    }
}
