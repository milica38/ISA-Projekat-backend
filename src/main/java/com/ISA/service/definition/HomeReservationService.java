package com.ISA.service.definition;

import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.HomeReservation;

import java.util.Date;
import java.util.List;

public interface HomeReservationService {
    HomeReservation add(HomeReservationDTO dto);
    List<HomeProfile> findAll();
    boolean isOverlapping(long houseId, Date startDate, Date endDate);
    List<HomeReservation> getMyReservations();
    boolean cancel(Long id);
    List<HomeFreeTerms> getAllAHousesOnAction();
    boolean canClientBook(Long currentClientId, Long houseId, Date startDate, Date endDate);


    }
