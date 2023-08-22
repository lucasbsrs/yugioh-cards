package com.lucas.yugiohcards.adapter.out.postgressql.repositories;

import com.lucas.yugiohcards.adapter.out.postgressql.entities.DataBaseVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBaseVersionRepository extends JpaRepository<DataBaseVersion, Long> {

}
