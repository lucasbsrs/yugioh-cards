package com.lucas.yugiohcards.adapter.out.postgressql.entities;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
public class SetCarta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nome;

    private String codigo;

    private String raridade;

    private String codigoRaridade;

    private String preco;

    @Column(name = "carta_id")
    private Long cartaId;

}
