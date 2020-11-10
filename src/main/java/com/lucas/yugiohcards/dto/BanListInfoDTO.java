package com.lucas.yugiohcards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class BanListInfoDTO implements Serializable {

    @JsonProperty("ban_tcg")
    private String statusTcg;

    @JsonProperty("ban_ocg")
    private String statusOcg;

    @JsonProperty("ban_goat")
    private String statusGoat;

}
