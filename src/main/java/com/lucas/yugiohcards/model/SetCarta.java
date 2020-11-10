package com.lucas.yugiohcards.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
public class SetCarta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigo;

    private String raridade;

    private String codigoRaridade;

    private String preco;

    @ManyToOne
    @JoinColumn(name = "carta_monstro_id", nullable = false)
    private CartaMonstro cartaMonstro;

}
