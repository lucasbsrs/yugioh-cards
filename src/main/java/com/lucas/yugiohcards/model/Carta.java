package com.lucas.yugiohcards.model;

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
public class Carta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nome;

    private String tipo;

    @Column(length = 2048)
    private String descricao;

    private BigDecimal ataque;

    private BigDecimal defesa;

    private Long level;

    private String raca;

    private String atributo;

    private String arquetipo;

    private Long escala;

    private Long linkValor;

    private String marcadorLink;

    private String statusBanListTcg;

    private String statusBanListOcg;

    private String statusBanListGoat;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carta_id")
    private List<SetCarta> setsCarta;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "carta_id")
//    private List<ImagemCarta> imagensCarta;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "carta_id")
//    private List<PrecoCarta> precosCarta;

}
