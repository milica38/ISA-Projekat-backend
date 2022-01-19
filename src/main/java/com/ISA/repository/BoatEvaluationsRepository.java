package com.ISA.repository;

import com.ISA.domain.model.BoatEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatEvaluationsRepository extends JpaRepository<BoatEvaluations, Long> {
}
