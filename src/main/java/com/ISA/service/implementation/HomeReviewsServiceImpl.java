package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeReviewsDTO;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.HomeReviews;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.repository.HomeReviewsRepository;
import com.ISA.service.definition.HomeReviewsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeReviewsServiceImpl implements HomeReviewsService {

    @Autowired
    HomeReviewsRepository homeReviewsRepository;

    @Autowired
    UserService userService;

    @Autowired
    HomeReservationRepository homeReservationRepository;


    @Override
    public HomeReviews add(HomeReviewsDTO dto) {

        HomeReservation reservation = homeReservationRepository.findById(dto.getHomeReservationId()).get();
        User currentUser = userService.getCurrentUser();

        HomeReviews reviews = new HomeReviews();
        reviews.setContent(dto.getContent());
        reviews.setOwnerId(currentUser.getId());
        reviews.setAppear(dto.isAppear());
        reviews.setBadComment(dto.isBadComment());
        reviews.setHomeReservation(reservation);

        return homeReviewsRepository.save(reviews);
    }
}
