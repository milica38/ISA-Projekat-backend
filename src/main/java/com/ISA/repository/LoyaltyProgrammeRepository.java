package com.ISA.repository;

import com.ISA.domain.model.LoyaltyProgramme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoyaltyProgrammeRepository extends JpaRepository<LoyaltyProgramme, Long> {

}
