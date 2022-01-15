package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatEvaluationsRepository;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.service.definition.BoatEvaluationsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BoatEvaluationsServiceImpl implements BoatEvaluationsService {

    @Autowired
    private BoatEvaluationsRepository boatEvaluationsRepository;

    @Autowired
    private BoatReservationRepository boatReservationRepository;

    @Autowired
    private BoatProfileRepository boatProfileRepository;

    @Autowired
    private UserService userService;

    @Override
    public boolean canUserSendEvaluations(Long currentClientId, Long boatReservationId) {
        List<BoatReservation> reservations = boatReservationRepository.findAll();

        Date currentDate = new Date();
        for(BoatReservation reservation: reservations){
            if(reservation.getCancelled() && reservation.getId().equals(boatReservationId) && reservation.getClientId().equals(currentClientId) && reservation.getEndDate().before(currentDate)){
                return false;
            }
        }
        return true;
    }

    @Override
    public BoatEvaluations add(BoatEvaluationsDTO dto) {

        BoatReservation reservation = boatReservationRepository.findById(dto.getBoatReservationId()).get();
        User currentUser = userService.getCurrentUser();

       /* if(canUserSendEvaluations(currentUser.getId(), dto.getHomeReservationId())){
            return null;
        }
        */
        BoatEvaluations evaluation = new BoatEvaluations();
        evaluation.setClientId(currentUser.getId());
        evaluation.setContent(dto.getContent());
        evaluation.setBoatReservation(reservation);
        evaluation.setRate(dto.getRate());

        int rateCount = reservation.getBoatProfile().getRateCounter();
        double rateAvg = reservation.getBoatProfile().getAvgRate();
        double sum = rateCount * rateAvg + dto.getRate();
        rateCount++;
        rateAvg = sum / rateCount;

        reservation.getBoatProfile().setRateCounter(rateCount);
        reservation.getBoatProfile().setAvgRate(rateAvg);

        boatProfileRepository.save(reservation.getBoatProfile());
        return boatEvaluationsRepository.save(evaluation);
    }
}
