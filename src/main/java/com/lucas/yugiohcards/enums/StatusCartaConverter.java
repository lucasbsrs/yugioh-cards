package com.lucas.yugiohcards.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusCartaConverter implements AttributeConverter<StatusCartaEnum, String> {

    @Override
    public String convertToDatabaseColumn(StatusCartaEnum statusCarta) {
        if (statusCarta != null) {
            return statusCarta.getCodigo();
        } else {
            return null;
        }
    }

    @Override
    public StatusCartaEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (StatusCartaEnum x : StatusCartaEnum.values()) {
            if (dbData.toUpperCase().equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + dbData);
    }

}

