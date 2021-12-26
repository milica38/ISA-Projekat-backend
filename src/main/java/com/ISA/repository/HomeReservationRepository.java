package com.ISA.repository;

import com.ISA.domain.model.HomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeReservationRepository extends JpaRepository<HomeReservation, Long> {
}
