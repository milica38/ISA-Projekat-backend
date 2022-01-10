package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatReservationDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatFreeTermsRepository;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.service.definition.BoatReservationService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoatReservationServiceImpl implements BoatReservationService {

    @Autowired
    private BoatReservationRepository reservationRepository;

    @Autowired
    private  BoatProfileRepository profileRepository;

    @Autowired
    private BoatFreeTermsRepository freeTermsRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Override
    public BoatReservation add(BoatReservationDTO dto) {

        BoatProfile boatProfile = profileRepository.findById(dto.getBoatId()).get();
        User currentUser = userService.getCurrentUser();

        if(isOverlapping(boatProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }

        if(!canClientBook(currentUser.getId(), dto.getBoatId(), dto.getStartDate(), dto.getEndDate() )){
            return null;
        }
        BoatReservation reservation = new BoatReservation();
        reservation.setExtraServices(boatProfile.getExtraService());
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setBoatProfile(boatProfile);
        reservation.setPrice(boatProfile.getPricelist());
        reservation.setClientId(currentUser.getId());


        emailService.sendEmailForBoatReservation(currentUser, reservation);

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

            if(reservation.getCancelled()) {
                continue;
            }

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

        return reservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
    }

    @Override
    public boolean cancel(Long id) {

        Optional<BoatReservation> reservation = reservationRepository.findById(id);
        Date today = new Date();

        if(reservation.get().getStartDate().before(today))
            return false;

        reservation.get().setCancelled(true);
        reservationRepository.save(reservation.get());
        return true;
    }

    @Override
    public List<BoatFreeTerms> getAllBoatsOnAction() {
        return freeTermsRepository.findAllByIsAction(true);
    }

    @Override
    public boolean canClientBook(Long currentClientId, Long boatId, Date startDate, Date endDate) {

        List<BoatReservation> reservations = reservationRepository.findAll();

        for(BoatReservation reservation: reservations) {
            System.out.println("==============================");
            System.out.println(reservation.getId());
            System.out.println(reservation.getCancelled());
            System.out.println(reservation.getBoatProfile().getId().equals(boatId));
            System.out.println(reservation.getClientId().equals(currentClientId));
            System.out.println(isDateEqual(reservation.getStartDate(), startDate));
            System.out.println(isDateEqual(reservation.getEndDate(), endDate));

            if (reservation.getCancelled() && reservation.getBoatProfile().getId().equals(boatId) && reservation.getClientId().equals(currentClientId) && isDateEqual(reservation.getStartDate(), startDate) && isDateEqual(reservation.getEndDate(), endDate)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }

}
