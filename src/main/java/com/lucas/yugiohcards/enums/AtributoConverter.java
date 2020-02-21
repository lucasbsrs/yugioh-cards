package com.lucas.yugiohcards.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AtributoConverter implements AttributeConverter<AtributoEnum, String> {

    @Override
    public String convertToDatabaseColumn(AtributoEnum atributo) {
        if (atributo != null) {
            return atributo.getCodigo();
        } else {
            return null;
        }
    }

    @Override
    public AtributoEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (AtributoEnum x : AtributoEnum.values()) {
            if (dbData.toUpperCase().equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + dbData);
    }

}

