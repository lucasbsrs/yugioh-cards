package com.lucas.yugiohcards.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucas.yugiohcards.dto.BanListInfoDTO;
import com.lucas.yugiohcards.enums.AtributoConverter;
import com.lucas.yugiohcards.enums.AtributoEnum;
import com.lucas.yugiohcards.enums.StatusCartaConverter;
import com.lucas.yugiohcards.enums.StatusCartaEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
public class CartaMonstro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

	@Column(name = "CODIGO")
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

}
