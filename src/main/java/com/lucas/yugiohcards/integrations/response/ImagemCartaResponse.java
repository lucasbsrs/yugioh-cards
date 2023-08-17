package com.lucas.yugiohcards.integrations.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class ImagemCartaResponse implements Serializable {

    @JsonProperty("id")
    private Long codigo;

    @JsonProperty("image_url")
    private String url;

    @JsonProperty("image_url_small")
    private String urlImagemPequena;

    @JsonProperty("image_url_cropped")
    private String urlImagemCortada;

}
