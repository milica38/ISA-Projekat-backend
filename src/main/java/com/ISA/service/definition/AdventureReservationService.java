package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.model.AdventureReservation;

import java.util.List;

public interface AdventureReservationService {
    List<AdventureReservation> getAll();
    AdventureReservation get(Long id);
    AdventureReservation add(AdventureReservationDTO adventureReservationDTO);
    AdventureReservation edit(AdventureReservationDTO adventurereservationDTO);
    boolean delete(Long id);
}
