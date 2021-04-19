package com.lucas.yugiohcards.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class SetCartaDTO implements Serializable {

    private String nome;

    private String codigo;

    private String raridade;

    private String codigoRaridade;

    private String preco;

}
