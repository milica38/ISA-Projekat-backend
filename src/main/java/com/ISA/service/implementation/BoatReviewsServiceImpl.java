package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatReviewsDTO;
import com.ISA.domain.model.BoatReservation;
import com.ISA.domain.model.BoatReviews;
import com.ISA.domain.model.User;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.repository.BoatReviewsRepository;
import com.ISA.service.definition.BoatReviewsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatReviewsServiceImpl implements BoatReviewsService {

    @Autowired
    BoatReviewsRepository boatReviewsRepository;

    @Autowired
    UserService userService;

    @Autowired
    BoatReservationRepository boatReservationRepository;

    @Override
    public BoatReviews add(BoatReviewsDTO dto) {

        BoatReservation reservation = boatReservationRepository.findById(dto.getBoatReservationId()).get();
        User currentUser = userService.getCurrentUser();

        BoatReviews reviews = new BoatReviews();
        reviews.setContent(dto.getContent());
        reviews.setOwnerId(currentUser.getId());
        reviews.setAppear(dto.isAppear());
        reviews.setBadComment(dto.isBadComment());
        reviews.setBoatReservation(reservation);

        return boatReviewsRepository.save(reviews);
    }
}
