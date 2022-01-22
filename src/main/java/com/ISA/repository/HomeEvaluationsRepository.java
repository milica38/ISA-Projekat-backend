package com.ISA.repository;

import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.HomeEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeEvaluationsRepository extends JpaRepository<HomeEvaluations, Long> {
    List<HomeEvaluations> findAllEvaluationsByIsApprovedAndIsDeclined(boolean isApproved, boolean isDeclined);

}
