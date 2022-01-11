package com.ISA.repository;

import com.ISA.domain.model.HomeComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeComplaintsRepository extends JpaRepository<HomeComplaints, Long> {
}
