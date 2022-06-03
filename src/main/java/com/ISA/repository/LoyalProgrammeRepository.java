package com.ISA.repository;

import com.ISA.domain.model.LoyalProgramme;
import com.ISA.domain.model.LoyaltyProgramme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyalProgrammeRepository extends JpaRepository<LoyalProgramme, Integer> {
}
