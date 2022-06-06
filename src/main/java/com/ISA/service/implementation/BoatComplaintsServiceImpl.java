package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.dto.BoatComplaintsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatComplaintsRepository;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.service.definition.BoatComplaintsService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoatComplaintsServiceImpl implements BoatComplaintsService {

    @Autowired
    private BoatReservationRepository boatReservationRepository;

    @Autowired
    private BoatComplaintsRepository boatComplaintsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @Override
    public boolean canUserSendComplaints(Long currentClientId, Long boatReservationId) {

        List<BoatReservation> reservations = boatReservationRepository.findAll();

        Date currentDate = new Date();
        for(BoatReservation reservation: reservations){
            if(reservation.getCancelled() == false && reservation.getId().equals(boatReservationId) && reservation.getClientId().equals(currentClientId) && reservation.getEndDate().before(currentDate)){
                return false;
            }
        }
        return true;    }

    @Override
    public BoatComplaints add(BoatComplaintsDTO dto) {

        BoatReservation reservation = boatReservationRepository.findById(dto.getBoatReservationId()).get();
        User currentUser = userService.getCurrentUser();

        if(canUserSendComplaints(currentUser.getId(), dto.getBoatReservationId())){
            return null;
        }

        BoatComplaints complaints = new BoatComplaints();
        complaints.setContent(dto.getContent());
        complaints.setClientId(currentUser.getId());
        complaints.setBoatReservation(reservation);

        return boatComplaintsRepository.save(complaints);    }

    public List<BoatComplaints> getAllBoatComplaints() {

        return boatComplaintsRepository.findAll();

    }

    public List<BoatComplaints> getAllComplaintsByComplaintResponse()
    {
        return boatComplaintsRepository.findAllByComplaintResponse("");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BoatComplaints responseToComplaint(BoatComplaintsDTO dto) throws Exception{

        Optional<BoatComplaints> optionalBoatComplaints = boatComplaintsRepository.findById(dto.getId());
        User user = userService.getUserById(optionalBoatComplaints.get().getClientId());
        optionalBoatComplaints.get().setComplaintResponse(dto.getComplaintResponse());
        emailService.sendEmailForBoatComplaintResponse(user,optionalBoatComplaints );
        return boatComplaintsRepository.save(optionalBoatComplaints.get());

    }
}
