package com.lucas.yugiohcards.adapter.out.postgressql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
public class Carta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(length = 1200, name = "descricao")
    private String descricao;

    @Column(name = "ataque")
    private BigDecimal ataque;

    @Column(name = "defesa")
    private BigDecimal defesa;

    @Column(name = "level")
    private Long level;

    @Column(name = "raca")
    private String raca;

    @Column(name = "atributo")
    private String atributo;

    @Column(name = "arquetipo")
    private String arquetipo;

    @Column(name = "escala")
    private Long escala;

    @Column(name = "link_valor")
    private Long linkValor;

    @Column(name = "marcador_link")
    private String marcadorLink;

    @Column(name = "status_ban_list_tcg")
    private String statusBanListTcg;

    @Column(name = "status_ban_list_ocg")
    private String statusBanListOcg;

    @Column(name = "status_ban_list_goat")
    private String statusBanListGoat;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carta_id")
    private List<ImagemCarta> imagensCarta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carta_id")
    private List<SetCarta> setsCarta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carta_id")
    private List<PrecoCarta> precosCarta;

}
