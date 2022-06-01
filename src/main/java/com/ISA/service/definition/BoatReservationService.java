package com.ISA.service.definition;

import com.ISA.domain.dto.BoatHistoryReservationDTO;
import com.ISA.domain.dto.BoatReservationDTO;
import com.ISA.domain.dto.HomeHistoryReservationDTO;
import com.ISA.domain.model.*;

import java.util.Date;
import java.util.List;

public interface BoatReservationService {
    BoatReservation add(BoatReservationDTO dto) throws Exception;
    BoatReservation addByOwner(BoatReservationDTO dto, Long clientId) throws Exception;
    List<BoatProfile> findAll();
    boolean isOverlapping(long boatId, Date startDate, Date endDate);
    List<BoatReservation> getMyReservations();
    boolean cancel(Long id);
    List<BoatFreeTerms> getAllBoatsOnAction();
    boolean canClientBook(Long currentClientId, Long boatId, Date startDate, Date endDate);
    BoatReservation get(Long id);
    List<BoatReservation> getAllReservationsForMyBoats(BoatHistoryReservationDTO dto);
    List<BoatReservation> getAllTodayReservationsForMyBoats(BoatHistoryReservationDTO dto);
    List<BoatReservation> getAllHistoryReservationsForMyBoats(BoatHistoryReservationDTO dto);
    List<BoatReservation> getMyFinishedReservations();
    List<BoatReservation> getMyUpcomingReservations();
    List<BoatReservation> getMyInProgressReservations();
    List<BoatReservation> getAllReservations(Long ownerId, Long boatId);
    List<BoatReservation> getAllReservationsForCharts(BoatHistoryReservationDTO dto);
    List<BoatReservation> getAllBoatReservationsForCharts(BoatHistoryReservationDTO dto);
}
