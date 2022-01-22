package com.ISA.repository;

import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.BoatEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatEvaluationsRepository extends JpaRepository<BoatEvaluations, Long> {
    List<BoatEvaluations> findAllEvaluationsByIsApprovedAndIsDeclined(boolean isApproved, boolean isDeclined);
}
