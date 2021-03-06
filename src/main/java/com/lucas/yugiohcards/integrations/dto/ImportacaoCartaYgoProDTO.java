package com.lucas.yugiohcards.integrations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ImportacaoCartaYgoProDTO implements Serializable {

    @JsonProperty("data")
    List<DadosCartaMonstroYgoProDTO> data;

}
