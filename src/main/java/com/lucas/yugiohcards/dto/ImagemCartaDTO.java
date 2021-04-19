package com.lucas.yugiohcards.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class ImagemCartaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long codigo;

    private String url;

    private String urlImagemPequena;

}
