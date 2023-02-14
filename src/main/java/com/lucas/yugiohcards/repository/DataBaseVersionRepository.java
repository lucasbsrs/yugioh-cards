package com.lucas.yugiohcards.repository;

import com.lucas.yugiohcards.model.DataBaseVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBaseVersionRepository extends JpaRepository<DataBaseVersion, Long> {

}
