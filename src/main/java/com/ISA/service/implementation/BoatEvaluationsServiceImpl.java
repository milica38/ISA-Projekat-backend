package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatEvaluationsRepository;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.service.definition.BoatEvaluationsService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private EmailService emailService;

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


    public List<BoatEvaluations> getAllBoatEvaluations() {

        return boatEvaluationsRepository.findAll();

    }

    public List<BoatEvaluations> getAllEvaluationsByApprovedAndDeclined()
    {
        return boatEvaluationsRepository.findAllEvaluationsByIsApprovedAndIsDeclined(false, false);
    }

    public Boolean evaluationApproved(Long id){
        Optional<BoatEvaluations> evaluation = boatEvaluationsRepository.findById(id);
        User user = userService.getUserById(evaluation.get().getBoatReservation().getBoatProfile().getownerId() );
        if(evaluation.isEmpty()){
            return false;
        }
        evaluation.get().setApproved(true);
        emailService.sendEmailForEvaluationApproved(user);
        boatEvaluationsRepository.save(evaluation.get());
        return true;
    }

    public Boolean evaluationDeclined(Long id)
    {
        Optional<BoatEvaluations> evaluation = boatEvaluationsRepository.findById(id);

        if(evaluation.isEmpty()){
            return false;
        }
        evaluation.get().setDeclined(true);
        boatEvaluationsRepository.save(evaluation.get());
        return true;
    }
}
