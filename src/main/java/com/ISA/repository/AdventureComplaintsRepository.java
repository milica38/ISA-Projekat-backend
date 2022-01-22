package com.ISA.repository;

import com.ISA.domain.model.AdventureComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureComplaintsRepository extends JpaRepository<AdventureComplaints, Long> {
    List<AdventureComplaints> findAllByComplaintResponse(String complaintResponse);
}
