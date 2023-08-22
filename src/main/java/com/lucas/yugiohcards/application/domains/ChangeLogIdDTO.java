package com.lucas.yugiohcards.application.domains;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChangeLogIdDTO {

    private String nome;

    private String oldId;

    private String newId;

    private LocalDateTime dataAtualizacao;

}
