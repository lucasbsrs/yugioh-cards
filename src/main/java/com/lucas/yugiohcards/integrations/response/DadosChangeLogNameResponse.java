package com.lucas.yugiohcards.integrations.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class DadosChangeLogNameResponse implements Serializable {

    @JsonProperty("old_name")
    private String nomeAntigo;

    @JsonProperty("new_name")
    private String nomeNovo;

    @JsonProperty("date")
    private String dataAtualizacao;

}
