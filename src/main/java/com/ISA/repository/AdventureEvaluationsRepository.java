package com.ISA.repository;

import com.ISA.domain.model.AdventureEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureEvaluationsRepository extends JpaRepository<AdventureEvaluations, Long> {
}
