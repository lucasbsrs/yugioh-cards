package com.lucas.yugiohcards.enums;

import lombok.Getter;

public enum AtributoEnum {
    DARK("DARK"),
    EARTH("EARTH"),
    FIRE("FIRE"),
    LIGHT("LIGHT"),
    WATER("WATER"),
    WIND("WIND"),
    DIVINE("DIVINE");

    @Getter
    private String codigo;

    AtributoEnum(String codigo) {
        this.codigo = codigo;
    }

}
