package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.enums.AttributeEnum;
import com.lucas.yugiohcards.enums.StatusCardEnum;
import com.lucas.yugiohcards.model.MonsterCard;
import com.lucas.yugiohcards.repository.MonsterCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private MonsterCardRepository monsterCardRepository;

    public void instantiateTestDatabase() throws Exception {

        MonsterCard m1 = new MonsterCard("Dragunity Dux", "LCKC-EN084", "Gains 200 ATK for each \"Dragunity\" card you control. When this card is Normal Summoned: You can target 1 Level 3 or lower Dragon \"Dragunity\" monster in your GY; equip that target to this card.",
                                        AttributeEnum.WIND, 4, "Winged Beast", true, 1500, 1000, StatusCardEnum.LIMITED);

        monsterCardRepository.save(m1);
    }
}
