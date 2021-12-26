package com.ISA.service.definition;

import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.HomeReservation;

public interface HomeReservationService {
    HomeReservation add(HomeReservationDTO dto);
}
