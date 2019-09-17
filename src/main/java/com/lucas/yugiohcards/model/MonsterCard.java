package com.lucas.yugiohcards.model;

import com.lucas.yugiohcards.enums.AttributeEnum;
import com.lucas.yugiohcards.enums.StatusCardEnum;

import javax.persistence.Entity;

@Entity
public class MonsterCard extends Card {

    private Long attribute;

    private Long level;

    private String type;

    private boolean effect;

    private Double attack;

    private Double defense;

    public MonsterCard() {

    }

    public MonsterCard(String name, String number, String description, AttributeEnum attribute, Long level, String type, boolean effect, Double attack, Double defense, StatusCardEnum statusCardEnum) {
        super(name, number, description, statusCardEnum.getCod());
        this.attribute = attribute == null ? null : attribute.getCod();
        this.level = level;
        this.type = type;
        this.effect = effect;
        this.attack = attack;
        this.defense = defense;
    }

    public Long getAttribute() {
        return attribute;
    }

    public void setAttribute(Long attribute) {
        this.attribute = attribute;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEffect() {
        return effect;
    }

    public void setEffect(boolean effect) {
        this.effect = effect;
    }

    public Double getAttack() {
        return attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }

    public Double getDefense() {
        return defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }
}
