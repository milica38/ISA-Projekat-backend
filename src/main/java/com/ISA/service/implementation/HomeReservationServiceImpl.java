package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.HomeReservation;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.service.definition.HomeReservationService;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeReservationServiceImpl implements HomeReservationService {

    @Autowired
    HomeReservationRepository homeReservationRepository;

    @Override
    public HomeReservation add(HomeReservationDTO dto) {

        HomeReservation reservation = new HomeReservation();
        reservation.setId(dto.getId());
        reservation.setExtraServices(dto.getExtraServices());
        reservation.setPrice(dto.getPrice());
        reservation.setCancelled(dto.getCancelled());
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setNumberOfDays(dto.getNumberOfDays());
        reservation.setNumberOfPeople(dto.getNumberOfPeople());

        return homeReservationRepository.save(reservation);
    }
}
