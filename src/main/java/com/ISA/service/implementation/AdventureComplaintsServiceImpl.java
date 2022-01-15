package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.AdventureComplaintsRepository;
import com.ISA.repository.AdventureReservationRepository;
import com.ISA.service.definition.AdventureComplaintsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdventureComplaintsServiceImpl implements AdventureComplaintsService {

    @Autowired
    private AdventureComplaintsRepository complaintsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdventureReservationRepository reservationRepository;

    @Override
    public boolean canUserSendComplaints(Long currentClientId, Long adventureReservationId) {
        List<AdventureReservation> reservations = reservationRepository.findAll();

        Date currentDate = new Date();
        for(AdventureReservation reservation: reservations){
            if(reservation.getCancelled() == false && reservation.getId().equals(adventureReservationId) && reservation.getClientId().equals(currentClientId) && reservation.getEndDate().before(currentDate)){
                return false;
            }
        }
        return true;
    }

    @Override
    public AdventureComplaints add(AdventureComplaintsDTO dto) {

        AdventureReservation reservation = reservationRepository.findById(dto.getAdventureReservationId()).get();
        User currentUser = userService.getCurrentUser();

        if(canUserSendComplaints(currentUser.getId(), dto.getAdventureReservationId())){
            return null;
        }

        AdventureComplaints complaints = new AdventureComplaints();
        complaints.setContent(dto.getContent());
        complaints.setClientId(currentUser.getId());
        complaints.setAdventureReservation(reservation);

        return complaintsRepository.save(complaints);
    }
}
