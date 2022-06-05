package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeReviewsDTO;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.HomeReviews;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeReservationRepository;
import com.ISA.repository.HomeReviewsRepository;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.HomeReviewsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import java.util.Date;



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

    @Autowired
    EmailService emailService;

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

    public List<HomeReviews> getAllHomeReviews() {

        return homeReviewsRepository.findAll();

    }

    public List<HomeReviews> getAllReviewsByOnePenalty()
    {
        return homeReviewsRepository.findAllReviewsByOnePenalty(false);
    }

    public List<HomeReviews> getAllReviewsByOnePenaltyAndBadComment()
    {
        return homeReviewsRepository.findAllReviewsByOnePenaltyAndIsBadComment(false,true);
    }

    public Boolean strikeOnePenalty(Long id){
        Optional<HomeReviews> review = homeReviewsRepository.findById(id);
        User user = userService.getUserById(review.get().getHomeReservation().getClientId() );
        User owner = userService.getUserById(review.get().getOwnerId() );
        if(review.isEmpty()){
            return false;
        }
        review.get().setPenalty(true);
        user.setPenalty(user.getPenalty()+1);
        emailService.sendEmailForPenaltyClient(user);
        emailService.sendEmailForPenaltyOwnerOrInstructor(owner);
        homeReviewsRepository.save(review.get());
        return true;
    }
}
