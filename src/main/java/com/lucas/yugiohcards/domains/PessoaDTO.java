package com.lucas.yugiohcards.domains;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PessoaDTO {

    private String nome;

    private String sobrenome;

    private String endCompleto;

    private String ci;

    private BigDecimal valor;

}
