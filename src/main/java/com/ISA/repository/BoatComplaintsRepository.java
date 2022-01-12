package com.ISA.repository;

import com.ISA.domain.model.BoatComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatComplaintsRepository extends JpaRepository<BoatComplaints, Long> {
}
