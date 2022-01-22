package com.ISA.repository;

import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureEvaluationsRepository extends JpaRepository<AdventureEvaluations, Long> {
    List<AdventureEvaluations> findAllEvaluationsByIsApprovedAndIsDeclined(boolean isApproved, boolean isDeclined);
}
