package com.lucas.yugiohcards.adapter.out.postgressql.repositories;

import com.lucas.yugiohcards.adapter.out.postgressql.entities.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {

    Carta findByNomeEquals(String nome);

    List<Carta> findByCodigoIn(List<String> codigos);

    List<Carta> findByNomeIn(List<String> nomes);

    List<Carta> findByCodigoNotIn(List<String> codigos);

}
