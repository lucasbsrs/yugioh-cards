package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
