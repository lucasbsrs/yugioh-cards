package com.lucas.yugiohcards.adapter.out.ygoclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class SetCartaYgoResponse implements Serializable {

    @JsonProperty("set_name")
    private String nome;

    @JsonProperty("set_code")
    private String codigo;

    @JsonProperty("set_rarity")
    private String raridade;

    @JsonProperty("set_rarity_code")
    private String codigoRaridade;

    @JsonProperty("set_price")
    private String preco;

}
