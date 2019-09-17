package com.lucas.yugiohcards.enums;

public enum AttributeEnum {
    DARK(1, "Dark"),
    EARTH(2, "Earth"),
    FIRE(3, "Fire"),
    LIGHT(4, "Light"),
    WATER(5, "Water"),
    WIND(6, "Wind"),
    DIVINE(7, "Divine");

    private long cod;
    private String description;

    private AttributeEnum(long cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public long getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static AttributeEnum toEnum(Long cod) {

        if(cod == null) {
            return null;
        }

        for (AttributeEnum x : AttributeEnum.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
