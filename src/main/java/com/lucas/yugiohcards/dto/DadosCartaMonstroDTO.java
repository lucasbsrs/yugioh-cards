package com.lucas.yugiohcards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class CartaMonstroDTO implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("desc")
    private String desc;

//    @NotEmpty(message = "Preenchimento obrigatório")
//    private String numero;

//    @NotEmpty(message = "Preenchimento obrigatório")
//    private String descricao;

//    @NotNull(message = "Preenchimento obrigatório")
//    private StatusCartaEnum status;
//
//    @NotNull(message = "Preenchimento obrigatório")
//    private AtributoEnum atributo;
//
//    @NotNull(message = "Preenchimento obrigatório")
//    private Long level;
//
//    @NotEmpty(message = "Preenchimento obrigatório")
//    private List<TipoCarta> tipoCartas;
//
//    private boolean efeito;
//
//    @NotNull(message = "Preenchimento obrigatório")
//    private Double ataque;
//
//    @NotNull(message = "Preenchimento obrigatório")
//    private Double defesa;

}
