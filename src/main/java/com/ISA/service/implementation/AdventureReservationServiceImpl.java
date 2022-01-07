package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.repository.AdventureReservationRepository;
import com.ISA.service.definition.AdventureReservationService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdventureReservationServiceImpl implements AdventureReservationService {

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Autowired
    private AdventureProfileRepository profileRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;


    @Override
    public AdventureReservation add(AdventureReservationDTO dto) {

        AdventureProfile adventureProfile = profileRepository.findById(dto.getAdventureId()).get();

        if(isOverlapping(adventureProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }
        AdventureReservation reservation = new AdventureReservation();
        reservation.setExtraServices(dto.getExtraServices());
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setClientId(userService.getCurrentUser().getId());
        reservation.setAdventureProfile(adventureProfile);
        adventureProfile.setPriceList(dto.getPrice());

        emailService.sendEmailForHouseReservation(userService.getCurrentUser());

        return adventureReservationRepository.save(reservation);
    }

    @Override
    public List<AdventureProfile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public boolean isOverlapping(long adventureId, Date startDate, Date endDate) {

        List<AdventureReservation> reservations = adventureReservationRepository.findAll();

        for(AdventureReservation reservation: reservations){

            if((startDate.equals(reservation.getStartDate()) || endDate.equals(reservation.getEndDate()) || (startDate.equals(reservation.getEndDate())) ||  (endDate.equals(reservation.getStartDate()))) && reservation.getAdventureProfile().getId().equals(adventureId)) {
                return true;
            }

            if(startDate.after(reservation.getStartDate()) && startDate.before(reservation.getEndDate()) && reservation.getAdventureProfile().getId().equals(adventureId)){
                return true;
            }

            if(endDate.after(reservation.getStartDate()) && endDate.before(reservation.getEndDate()) && reservation.getAdventureProfile().getId().equals(adventureId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AdventureReservation> getMyReservations() {
        User user = userService.getCurrentUser();

        return adventureReservationRepository.getAllByClientId(user.getId());
    }

    @Override
    public boolean cancel(Long id) {
        return false;
    }
}
