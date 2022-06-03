package com.ISA.repository;

import com.ISA.domain.model.HomeComplaints;
import com.ISA.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
  Optional<Income> findByPercentage(double percentage);
}
