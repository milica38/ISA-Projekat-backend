package com.ISA.repository;

import com.ISA.domain.model.HomeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HomeReservationRepository extends JpaRepository<HomeReservation, Long> {
    List<HomeReservation> getAllByClientIdAndCancelled(Long clientId, boolean cancelled);
    List<HomeReservation> getAllByHomeProfileId(Long homeProfileId);
}
