package com.lucas.yugiohcards.application.domains;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChangeLogNameDTO {

    private String nomeAntigo;

    private String nomeNovo;

    private LocalDateTime dataAtualizacao;

}
