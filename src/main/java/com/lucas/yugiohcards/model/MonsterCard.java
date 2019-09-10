package com.lucas.yugiohcards.model;

import com.lucas.yugiohcards.enums.AttributeEnum;

import javax.persistence.Entity;

@Entity
public class MonsterCard extends Card {

    private String attribute;

    private Integer level;

    private String type;

    private boolean effect;

    private Integer attack;

    private Integer defense;

    public MonsterCard(AttributeEnum attribute, Integer level, String type, boolean effect, Integer attack, Integer defense) {
        this.attribute = attribute == null ? null : attribute.getDescription();
        this.level = level;
        this.type = type;
        this.effect = effect;
        this.attack = attack;
        this.defense = defense;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
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

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
