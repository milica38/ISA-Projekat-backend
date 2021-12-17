package com.ISA.repository;


import com.ISA.domain.model.AdventureReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {
}
