package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.TipoCarta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCartaRepository extends JpaRepository<TipoCarta, Long>  {
}
