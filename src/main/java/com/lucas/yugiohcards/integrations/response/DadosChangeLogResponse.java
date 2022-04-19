package com.lucas.yugiohcards.integrations.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class DadosChangeLogResponse implements Serializable {

    @JsonProperty("name")
    private String nome;

    @JsonProperty("old_id")
    private String oldId;

    @JsonProperty("new_id")
    private String newId;

    @JsonProperty("date")
    private String date;

}
