package com.lucas.yugiohcards.application.domains;

import com.lucas.yugiohcards.adapter.out.postgressql.entities.ImagemCarta;
import com.lucas.yugiohcards.adapter.out.postgressql.entities.SetCarta;

import java.math.BigDecimal;
import java.util.Set;

public record CartaRecord(Long id, String codigo, String nome, String tipo, String descricao, BigDecimal ataque,
                          BigDecimal defesa, Long level, String raca, String atributo, String arquetipo,
                          Long escala, Long linkValor, String marcadorLink, String statusBanListTcg,
                          String statusBanListOcg, Set<SetCarta> setsCarta,
                          Set<ImagemCarta> imagensCarta) {}
