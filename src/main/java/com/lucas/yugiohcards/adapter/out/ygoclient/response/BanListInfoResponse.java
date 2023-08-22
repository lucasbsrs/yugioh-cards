package com.lucas.yugiohcards.adapter.out.ygoclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class BanListInfoResponse implements Serializable {

    @JsonProperty("ban_tcg")
    private String statusTcg;

    @JsonProperty("ban_ocg")
    private String statusOcg;

    @JsonProperty("ban_goat")
    private String statusGoat;

}
