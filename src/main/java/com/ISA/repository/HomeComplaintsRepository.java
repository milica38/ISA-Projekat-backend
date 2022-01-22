package com.ISA.repository;

import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.HomeComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeComplaintsRepository extends JpaRepository<HomeComplaints, Long> {
    List<HomeComplaints> findAllByComplaintResponse(String complaintResponse);
}
