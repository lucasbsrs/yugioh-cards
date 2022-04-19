package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {

    Carta findByNomeEquals(String nome);

    List<Carta> findByCodigoIn(List<String> codigos);

}
