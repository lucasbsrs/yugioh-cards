package com.lucas.yugiohcards.model;

import lombok.*;

import javax.persistence.*;
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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "carta_id")
//    private Carta carta;

}
