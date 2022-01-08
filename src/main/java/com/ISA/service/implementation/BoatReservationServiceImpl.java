package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatReservationDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.service.definition.BoatReservationService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.util.Date;
import java.util.List;

@Service
public class BoatReservationServiceImpl implements BoatReservationService {

    @Autowired
    private BoatReservationRepository reservationRepository;

    @Autowired
    private  BoatProfileRepository profileRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Override
    public BoatReservation add(BoatReservationDTO dto) {

        BoatProfile boatProfile = profileRepository.findById(dto.getBoatId()).get();

        if(isOverlapping(boatProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }
        BoatReservation reservation = new BoatReservation();
        reservation.setExtraServices(boatProfile.getExtraService());
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setBoatProfile(boatProfile);
        reservation.setPrice(boatProfile.getPricelist());
        reservation.setClientId(userService.getCurrentUser().getId());


        emailService.sendEmailForBoatReservation(userService.getCurrentUser(), reservation);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<BoatProfile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public boolean isOverlapping(long boatId, Date startDate, Date endDate) {

        List<BoatReservation> reservations = reservationRepository.findAll();

        for(BoatReservation reservation: reservations){

            if((startDate.equals(reservation.getStartDate()) || endDate.equals(reservation.getEndDate()) || (startDate.equals(reservation.getEndDate())) ||  (endDate.equals(reservation.getStartDate()))) && reservation.getBoatProfile().getId().equals(boatId)) {
                return true;
            }

            if(startDate.after(reservation.getStartDate()) && startDate.before(reservation.getEndDate()) && reservation.getBoatProfile().getId().equals(boatId)){
                return true;
            }

            if(endDate.after(reservation.getStartDate()) && endDate.before(reservation.getEndDate()) && reservation.getBoatProfile().getId().equals(boatId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BoatReservation> getMyReservations() {
        User user = userService.getCurrentUser();

        return reservationRepository.getAllByClientId(user.getId());
    }

    @Override
    public boolean cancel(Long id) {
        return false;
    }
}
