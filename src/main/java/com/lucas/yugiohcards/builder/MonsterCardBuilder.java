package com.lucas.yugiohcards.builder;

import com.lucas.yugiohcards.enums.AttributeEnum;
import com.lucas.yugiohcards.enums.StatusCardEnum;
import com.lucas.yugiohcards.model.MonsterCard;

public class MonsterCardBuilder {

    private MonsterCard monsterCard;

    public MonsterCardBuilder(){
        this.monsterCard = new MonsterCard();
    }

    public static MonsterCardBuilder builder() {
        return new MonsterCardBuilder();
    }

    public MonsterCardBuilder name(String name) {
        this.monsterCard.setName(name);
        return this;
    }

    public MonsterCardBuilder number(String number) {
        this.monsterCard.setNumber(number);
        return this;
    }

    public MonsterCardBuilder description(String description) {
        this.monsterCard.setDescription(description);
        return this;
    }

    public MonsterCardBuilder status(StatusCardEnum statusCardEnum) {
        this.monsterCard.setStatus(statusCardEnum.getDescription());
        return this;
    }

    public MonsterCardBuilder attribute(AttributeEnum attributeEnum) {
        this.monsterCard.setAttribute(attributeEnum.getDescription());
        return this;
    }

    public MonsterCardBuilder level(Integer level) {
        this.monsterCard.setLevel(level);
        return this;
    }

    public MonsterCardBuilder type(String type) {
        this.monsterCard.setType(type);
        return this;
    }

    public MonsterCardBuilder effect(boolean effect) {
        this.monsterCard.setEffect(effect);
        return this;
    }

    public MonsterCardBuilder attack(Integer attack) {
        this.monsterCard.setAttack(attack);
        return this;
    }

    public MonsterCardBuilder defense(Integer defense) {
        this.monsterCard.setDefense(defense);
        return this;
    }

    public MonsterCard get() {
        return this.monsterCard;
    }

}
