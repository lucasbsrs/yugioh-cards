package com.lucas.yugiohcards.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class PrecoCartaDTO implements Serializable {

    private String cardmarket;

    private String tcgplayer;

    private String ebay;

    private String amazon;

    private String coolstuffinc;

}
