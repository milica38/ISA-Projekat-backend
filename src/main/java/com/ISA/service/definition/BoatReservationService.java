package com.ISA.service.definition;

import com.ISA.domain.dto.BoatReservationDTO;
import com.ISA.domain.model.*;

import java.util.Date;
import java.util.List;

public interface BoatReservationService {
    BoatReservation add(BoatReservationDTO dto);
    List<BoatProfile> findAll();
    boolean isOverlapping(long boatId, Date startDate, Date endDate);
    List<BoatReservation> getMyReservations();
    boolean cancel(Long id);
    List<BoatFreeTerms> getAllBoatsOnAction();
    boolean canClientBook(Long currentClientId, Long boatId, Date startDate, Date endDate);
    BoatReservation get(Long id);


}
