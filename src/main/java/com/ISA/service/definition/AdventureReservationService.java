package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureHistoryReservationsDTO;
import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.dto.HomeHistoryReservationDTO;
import com.ISA.domain.model.*;

import java.util.Date;
import java.util.List;

public interface AdventureReservationService {

    AdventureReservation add(AdventureReservationDTO dto);
    List<AdventureProfile> findAll();
    boolean isOverlapping(long adventureId, Date startDate, Date endDate);
    List<AdventureReservation> getMyReservations();
    boolean cancel(Long id);
    List<AdventureFreeTerms> getAllAdventuresOnAction();
    boolean canClientBook(Long currentClientId, Long adventureId, Date startDate, Date endDate);
    AdventureReservation get(Long id);
    List<AdventureReservation> getAllReservationsForMyAdventures(AdventureHistoryReservationsDTO dto);
    List<AdventureReservation> getAllTodayReservationsForMyAdventures(AdventureHistoryReservationsDTO dto);
    List<AdventureReservation> getAllHistoryReservationsForMyAdventures(AdventureHistoryReservationsDTO dto);
    List<AdventureReservation> getAllAdventureReservations(Long instructorId, Long adventureId);
    List<AdventureReservation> getMyFinishedReservations();
    List<AdventureReservation> getMyUpcomingReservatons();
    List<AdventureReservation> getMyInProgressReservations();
}
