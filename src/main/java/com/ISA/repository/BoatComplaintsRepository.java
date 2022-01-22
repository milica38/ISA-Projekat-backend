package com.ISA.repository;

import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.BoatComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatComplaintsRepository extends JpaRepository<BoatComplaints, Long> {
    List<BoatComplaints> findAllByComplaintResponse(String complaintResponse);
}
