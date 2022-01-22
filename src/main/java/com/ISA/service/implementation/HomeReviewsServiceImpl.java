package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeReviewsDTO;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.HomeReviews;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.repository.HomeReviewsRepository;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.HomeReviewsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HomeReviewsServiceImpl implements HomeReviewsService {

    @Autowired
    HomeReviewsRepository homeReviewsRepository;

    @Autowired
    UserService userService;

    @Autowired
    HomeReservationRepository homeReservationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public HomeReviews add(HomeReviewsDTO dto) {

        HomeReservation reservation = homeReservationRepository.findById(dto.getHomeReservationId()).get();
        User currentUser = userService.getCurrentUser();
        List <HomeReviews> writedReviews = homeReviewsRepository.findAll();

        for(HomeReviews r: writedReviews){
            if(r.getHomeReservation().getCancelled() || r.getHomeReservation().getWrited()){
                return null;
            }
        }

        HomeReviews reviews = new HomeReviews();
        reviews.setContent(dto.getContent());
        reviews.setOwnerId(currentUser.getId());
        reviews.setAppear(dto.isAppear());
        reviews.setBadComment(dto.isBadComment());
        reviews.setHomeReservation(reservation);
        reviews.getHomeReservation().setWrited(true);

        if(dto.isAppear()) {
            currentUser.setPenalty(currentUser.getPenalty() + 1);
            userRepository.save(currentUser);
        }

        if(dto.isBadComment()) {
            currentUser.setPenalty(currentUser.getPenalty() + 1);
            userRepository.save(currentUser);
        }

        return homeReviewsRepository.save(reviews);
    }
}
