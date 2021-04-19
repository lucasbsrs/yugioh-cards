package com.lucas.yugiohcards.integrations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucas.yugiohcards.dto.BanListInfoDTO;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class DadosCartaMonstroYgoProDTO implements Serializable {

    @JsonProperty("id")
    private String codigo;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("type")
    private String tipo;

    @JsonProperty("desc")
    private String descricao;

    @JsonProperty("atk")
    private BigDecimal ataque;

    @JsonProperty("def")
    private BigDecimal defesa;

    @JsonProperty("level")
    private Long level;

    @JsonProperty("race")
    private String raca;

    @JsonProperty("attribute")
    private String atributo;

    @JsonProperty("archetype")
    private String arquetipo;

    @JsonProperty("scale")
    private Long escala;

    @JsonProperty("linkval")
    private Long linkValor;

    @JsonProperty("linkmarkers")
    private List<String> marcadorLink;

    @JsonProperty("banlist_info")
    private BanListInfoDTO banListInfo;

    @JsonProperty("card_sets")
    private List<SetCartaYgoProDTO> setsCarta;

    @JsonProperty("card_images")
    private List<ImagemCartaYgoProDTO> imagensCarta;

    @JsonProperty("card_prices")
    private List<PrecoCartaYgoProDTO> precosCarta;

}
