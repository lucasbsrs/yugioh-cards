package com.lucas.yugiohcards.enums;

public enum StatusCardEnum {

    LIMITED(1, "Limited");

    private int cod;
    private String description;

    private StatusCardEnum(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static StatusCardEnum toEnum(Integer cod) {

        if(cod == null) {
            return null;
        }

        for (StatusCardEnum x : StatusCardEnum.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
