package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {

    Carta findByNomeEquals(String nome);

}
