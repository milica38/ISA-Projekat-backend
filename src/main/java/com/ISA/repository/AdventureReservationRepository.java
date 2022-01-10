package com.ISA.repository;


import com.ISA.domain.model.AdventureReservation;
import com.ISA.domain.model.HomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {
    List<AdventureReservation> getAllByClientIdAndCancelled(Long clientId, boolean cancelled);

}
