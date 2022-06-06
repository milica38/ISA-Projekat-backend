package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeEvaluationsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.HomeEvaluationsRepository;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.HomeEvaluationsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HomeEvaluationsServiceImpl implements HomeEvaluationsService {

    @Autowired
    HomeReservationRepository homeReservationRepository;

    @Autowired
    HomeEvaluationsRepository homeEvaluationsRepository;

    @Autowired
    UserService userService;

    @Autowired
    HomeProfileRepository homeProfileRepository;

    @Autowired
    EmailService emailService;


    @Override
    public boolean canUserSendEvaluations(Long currentClientId, Long houseReservationId) {
        List<HomeReservation> reservations = homeReservationRepository.findAll();

        Date currentDate = new Date();
        for(HomeReservation reservation: reservations){
            if(reservation.getCancelled() && reservation.getId().equals(houseReservationId) && reservation.getClientId().equals(currentClientId) && reservation.getEndDate().before(currentDate)){
                return false;
            }
        }
        return true;
    }

    @Override
    public HomeEvaluations add(HomeEvaluationsDTO dto) {

        HomeReservation reservation = homeReservationRepository.findById(dto.getHomeReservationId()).get();
        User currentUser = userService.getCurrentUser();

       /* if(canUserSendEvaluations(currentUser.getId(), dto.getHomeReservationId())){
            return null;
        }
*/
        HomeEvaluations evaluation = new HomeEvaluations();
        evaluation.setClientId(currentUser.getId());
        evaluation.setContent(dto.getContent());
        evaluation.setHomeReservation(reservation);
        evaluation.setRate(dto.getRate());

        int rateCount = reservation.getHomeProfile().getRateCounter();
        double rateAvg = reservation.getHomeProfile().getAvgRate();
        double sum = rateCount * rateAvg + dto.getRate();
        rateCount++;
        rateAvg = sum / rateCount;

        reservation.getHomeProfile().setRateCounter(rateCount);
        reservation.getHomeProfile().setAvgRate(rateAvg);

        homeProfileRepository.save(reservation.getHomeProfile());

        return homeEvaluationsRepository.save(evaluation);
    }


    public List<HomeEvaluations> getAllHomeEvaluations() {

        return homeEvaluationsRepository.findAll();

    }

    public List<HomeEvaluations> getAllEvaluationsByApprovedAndDeclined()
    {
        return homeEvaluationsRepository.findAllEvaluationsByIsApprovedAndIsDeclined(false, false);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean evaluationApproved(Long id) {
        Optional<HomeEvaluations> evaluation = homeEvaluationsRepository.findById(id);
        User user = userService.getUserById(evaluation.get().getHomeReservation().getHomeProfile().getownerId());
        if(evaluation.isEmpty()){
            return false;
        }
        evaluation.get().setApproved(true);
        emailService.sendEmailForEvaluationApproved(user);
        homeEvaluationsRepository.save(evaluation.get());
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean evaluationDeclined(Long id)
    {
        Optional<HomeEvaluations> evaluation = homeEvaluationsRepository.findById(id);

        if(evaluation.isEmpty()){
            return false;
        }
        evaluation.get().setDeclined(true);
        homeEvaluationsRepository.save(evaluation.get());
        return true;
    }

}
