package com.ISA.repository;

import com.ISA.domain.model.BoatReservation;
import com.ISA.domain.model.HomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long> {
    List<BoatReservation> getAllByClientId(Long clientId);

}
