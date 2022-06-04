package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureEvaluationsDTO;
import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.AdventureEvaluationsRepository;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.repository.AdventureReservationRepository;
import com.ISA.service.definition.AdventureEvaluationsService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdventureEvaluationsServiceImpl implements AdventureEvaluationsService {

   @Autowired
    private AdventureEvaluationsRepository adventureEvaluationsRepository;

   @Autowired
    private AdventureReservationRepository adventureReservationRepository;

   @Autowired
   private AdventureProfileRepository adventureProfileRepository;
   @Autowired
   private UserService userService;

   @Autowired
   private EmailService emailService;

   @Override
    public boolean canUserSendEvaluations(Long currentClientId, Long adventureReservationId) {
       List<AdventureReservation> reservations = adventureReservationRepository.findAll();

       Date currentDate = new Date();
       for(AdventureReservation reservation: reservations){
           if(reservation.getCancelled() && reservation.getId().equals(adventureReservationId) && reservation.getClientId().equals(currentClientId) && reservation.getEndDate().before(currentDate)){
               return false;
           }
       }
       return true;
    }

    @Override
    public AdventureEvaluations add(AdventureEvaluationsDTO dto) {

        AdventureReservation reservation = adventureReservationRepository.findById(dto.getAdventureReservationId()).get();
        User currentUser = userService.getCurrentUser();

       /* if(canUserSendEvaluations(currentUser.getId(), dto.getHomeReservationId())){
            return null;
        }
*/
        AdventureEvaluations evaluation = new AdventureEvaluations();
        evaluation.setClientId(currentUser.getId());
        evaluation.setContent(dto.getContent());
        evaluation.setAdventureReservation(reservation);
        evaluation.setRate(dto.getRate());

        int rateCount = reservation.getAdventureProfile().getRateCounter();
        double rateAvg = reservation.getAdventureProfile().getAvgRate();
        double sum = rateCount * rateAvg + dto.getRate();
        rateCount++;
        rateAvg = sum / rateCount;

        reservation.getAdventureProfile().setRateCounter(rateCount);
        reservation.getAdventureProfile().setAvgRate(rateAvg);

        adventureProfileRepository.save(reservation.getAdventureProfile());
        return adventureEvaluationsRepository.save(evaluation);
    }


    public List<AdventureEvaluations> getAllAdventureEvaluations() {

        return adventureEvaluationsRepository.findAll();

    }

    public List<AdventureEvaluations> getAllEvaluationsByApprovedAndDeclined()
    {
        return adventureEvaluationsRepository.findAllEvaluationsByIsApprovedAndIsDeclined(false, false);
    }

    public Boolean evaluationApproved(Long id){
        Optional<AdventureEvaluations> evaluation = adventureEvaluationsRepository.findById(id);
        User user = userService.getUserById(evaluation.get().getAdventureReservation().getAdventureProfile().getInstructorId());
        if(evaluation.isEmpty()){
            return false;
        }
        evaluation.get().setApproved(true);
        emailService.sendEmailForEvaluationApproved(user);
        adventureEvaluationsRepository.save(evaluation.get());
        return true;
    }

    public Boolean evaluationDeclined(Long id)
    {
        Optional<AdventureEvaluations> evaluation = adventureEvaluationsRepository.findById(id);

        if(evaluation.isEmpty()){
            return false;
        }
        evaluation.get().setDeclined(true);
        adventureEvaluationsRepository.save(evaluation.get());
        return true;
    }
}
