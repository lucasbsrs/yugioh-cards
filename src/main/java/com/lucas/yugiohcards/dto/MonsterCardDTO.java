package com.lucas.yugiohcards.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class MonsterCardDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String number;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String description;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String status;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String attribute;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Integer level;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String type;

    private boolean effect;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Integer attack;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Integer defense;

    public MonsterCardDTO() {
    }

    public MonsterCardDTO(String name, String number, String description, String status, String attribute, Integer level, String type, boolean effect, Integer attack, Integer defense) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.status = status;
        this.attribute = attribute;
        this.level = level;
        this.type = type;
        this.effect = effect;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
