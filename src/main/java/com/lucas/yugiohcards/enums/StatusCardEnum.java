package com.lucas.yugiohcards.enums;

public enum StatusCardEnum {

    LIMITED(1, "Limited"),
    SEMI_LIMITED(2, "Semi-Limited"),
    FORBIDDEN(3, "Forbidden");

    private long cod;
    private String description;

    private StatusCardEnum(long cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public long getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static StatusCardEnum toEnum(Long cod) {

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
