package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.MonsterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterCardRepository extends JpaRepository<MonsterCard, Long> {
}
