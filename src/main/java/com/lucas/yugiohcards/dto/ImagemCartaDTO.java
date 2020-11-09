package com.lucas.yugiohcards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class ImagemCartaDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("image_url")
    private String url;

    @JsonProperty("image_url_small")
    private String urlImagemPequena;

}
