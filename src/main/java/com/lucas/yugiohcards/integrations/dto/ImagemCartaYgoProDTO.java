package com.lucas.yugiohcards.integrations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class ImagemCartaYgoProDTO implements Serializable {

    @JsonProperty("id")
    private Long codigo;

    @JsonProperty("image_url")
    private String url;

    @JsonProperty("image_url_small")
    private String urlImagemPequena;

}
