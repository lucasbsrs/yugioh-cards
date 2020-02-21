package com.lucas.yugiohcards.model;

import com.lucas.yugiohcards.enums.AtributoConverter;
import com.lucas.yugiohcards.enums.AtributoEnum;
import com.lucas.yugiohcards.enums.StatusCartaConverter;
import com.lucas.yugiohcards.enums.StatusCartaEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    private String nome;

    private String numero;

    private String descricao;

    @Convert(converter = StatusCartaConverter.class)
    private StatusCartaEnum status;

    @Convert(converter = AtributoConverter.class)
    private AtributoEnum atributo;

    private Long level;

    @OneToMany
    private List<TipoCarta> tipoCartas;

    private Double ataque;

    private Double defesa;

}
