package com.lucas.yugiohcards.domains;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChangeLogDTO {

    private String nome;

    private String oldId;

    private String newId;

    private LocalDateTime dataAtualizacao;

}
