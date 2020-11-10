package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.SetCarta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetCartaRepository extends JpaRepository<SetCarta, Long> {

}
