package com.lucas.yugiohcards.adapter.out.ygoclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class PrecoCartaResponse implements Serializable {

    @JsonProperty("cardmarket_price")
    private String cardmarket;

    @JsonProperty("tcgplayer_price")
    private String tcgplayer;

    @JsonProperty("ebay_price")
    private String ebay;

    @JsonProperty("amazon_price")
    private String amazon;

    @JsonProperty("coolstuffinc_price")
    private String coolstuffinc;

}
