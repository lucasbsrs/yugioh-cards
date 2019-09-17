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
    private Long status;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Long attribute;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Long level;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String type;

    private boolean effect;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Double attack;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Double defense;

    public MonsterCardDTO() {
    }

    public MonsterCardDTO(String name, String number, String description, Long status, Long attribute, Long level, String type, boolean effect, Double attack, Double defense) {
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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
