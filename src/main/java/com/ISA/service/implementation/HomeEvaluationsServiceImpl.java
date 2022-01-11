package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeEvaluationsDTO;
import com.ISA.domain.model.HomeEvaluations;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeEvaluationsRepository;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.service.definition.HomeEvaluationsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HomeEvaluationsServiceImpl implements HomeEvaluationsService {

    @Autowired
    HomeReservationRepository homeReservationRepository;

    @Autowired
    HomeEvaluationsRepository homeEvaluationsRepository;

    @Autowired
    UserService userService;


    @Override
    public boolean canUserSendEvaluations(Long currentClientId, Long houseReservationId) {
        List<HomeReservation> reservations = homeReservationRepository.findAll();

        Date currentDate = new Date();
        for(HomeReservation reservation: reservations){
            if(reservation.getCancelled() == false && reservation.getId().equals(houseReservationId) && reservation.getClientId().equals(currentClientId) && reservation.getEndDate().before(currentDate)){
                return false;
            }
        }
        return true;
    }

    @Override
    public HomeEvaluations add(HomeEvaluationsDTO dto) {

        HomeReservation reservation = homeReservationRepository.findById(dto.getHomeReservationId()).get();
        User currentUser = userService.getCurrentUser();

        if(canUserSendEvaluations(currentUser.getId(), dto.getHomeReservationId())){
            return null;
        }

        HomeEvaluations evaluation = new HomeEvaluations();
        evaluation.setClientId(currentUser.getId());
        evaluation.setContent(dto.getContent());
        evaluation.setHomeReservation(reservation);
        evaluation.setRate(dto.getRate());

        return homeEvaluationsRepository.save(evaluation);
    }
}
