package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.CartaMonstro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaMonstroRepository extends JpaRepository<CartaMonstro, Long> {

    CartaMonstro findByNomeEquals(String nome);

}
