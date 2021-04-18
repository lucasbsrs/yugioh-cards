package com.lucas.yugiohcards.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class CartaMonstroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private String codigo;

    private String nome;

    private String tipo;

    private BigDecimal ataque;

    private BigDecimal defesa;

    private Long level;

    private String raca;

    private String atributo;

    private String arquetipo;

    private Long escala;

    private Long linkValor;

    private String statusBanListTcg;

    private String statusBanListOcg;

    private String statusBanListGoat;

    private List<SetCartaDTO> setCarta;

}
