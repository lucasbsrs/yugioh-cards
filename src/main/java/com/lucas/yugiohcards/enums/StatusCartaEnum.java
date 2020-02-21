package com.lucas.yugiohcards.enums;

import lombok.Getter;

public enum StatusCartaEnum {

    LIMITED("LIMITED"),
    SEMI_LIMITED("SEMI-LIMITED"),
    FORBIDDEN("FORBIDDEN"),
    UNLIMITED("UNLIMITED");

    @Getter
    private String codigo;

     StatusCartaEnum(String codigo) {
        this.codigo = codigo;
    }
}
