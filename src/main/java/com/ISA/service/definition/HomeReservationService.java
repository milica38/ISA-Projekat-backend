package com.ISA.service.definition;

import com.ISA.domain.dto.HomeHistoryReservationDTO;
import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.HomeReservation;

import java.util.Date;
import java.util.List;

public interface HomeReservationService {
    HomeReservation add(HomeReservationDTO dto);
    HomeReservation addByOwner(HomeReservationDTO dto, Long clientId);
    List<HomeProfile> findAll();
    boolean isOverlapping(long houseId, Date startDate, Date endDate);
    List<HomeReservation> getMyReservations();
    boolean cancel(Long id);
    List<HomeFreeTerms> getAllHousesOnAction();
    boolean canClientBook(Long currentClientId, Long houseId, Date startDate, Date endDate);
    List<HomeReservation> getAllReservationsForMyHouses(HomeHistoryReservationDTO dto);
    List<HomeReservation> getAllTodayReservationsForMyHouses(HomeHistoryReservationDTO dto);
    List<HomeReservation> getAllHistoryReservationsForMyHouses(HomeHistoryReservationDTO dto);
    List<HomeReservation> getAllReservationsForCharts(HomeHistoryReservationDTO dto);
    List<HomeReservation> getAll();
    List<HomeReservation> getAllReservations(Long ownerId, Long houseId);
    HomeReservation get(Long id);
    List<HomeReservation> getMyFinishedReservations();
    List<HomeReservation> getMyUpcomingReservatons();
    List<HomeReservation> getMyInProgressReservations();

}
