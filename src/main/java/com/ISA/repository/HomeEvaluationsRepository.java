package com.ISA.repository;

import com.ISA.domain.model.HomeEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeEvaluationsRepository extends JpaRepository<HomeEvaluations, Long> {


}
