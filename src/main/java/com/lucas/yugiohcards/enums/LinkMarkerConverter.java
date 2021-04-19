package com.lucas.yugiohcards.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LinkMarkerConverter implements AttributeConverter<LinkMakerEnum, String> {

    @Override
    public String convertToDatabaseColumn(LinkMakerEnum atributo) {
        if (atributo != null) {
            return atributo.getCodigo();
        } else {
            return null;
        }
    }

    @Override
    public LinkMakerEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (LinkMakerEnum x : LinkMakerEnum.values()) {
            if (dbData.toUpperCase().equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + dbData);
    }

}

