package com.ISA.repository;

import com.ISA.domain.model.AdventureComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureComplaintsRepository extends JpaRepository<AdventureComplaints, Long> {
}
