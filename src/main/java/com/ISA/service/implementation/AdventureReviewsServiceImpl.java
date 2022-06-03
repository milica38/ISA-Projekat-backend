package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureReviewsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.AdventureReservationRepository;
import com.ISA.repository.AdventureReviewsRepository;
import com.ISA.service.definition.AdventureReviewsService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdventureReviewsServiceImpl implements AdventureReviewsService {
    @Autowired
    AdventureReviewsRepository adventureReviewsRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    AdventureReservationRepository adventureReservationRepository;


@Override
    public AdventureReviews add(AdventureReviewsDTO dto) {

        AdventureReservation reservation = adventureReservationRepository.findById(dto.getAdventureReservationId()).get();
        User currentUser = userService.getCurrentUser();

        AdventureReviews reviews = new AdventureReviews();
        reviews.setContent(dto.getContent());
        reviews.setInstructorId(currentUser.getId());
        reviews.setAppear(dto.isAppear());
        reviews.setBadComment(dto.isBadComment());
        reviews.setAdventureReservation(reservation);

        return adventureReviewsRepository.save(reviews);
    }

    public List<AdventureReviews> getAllAdventureReviews() {

        return adventureReviewsRepository.findAll();

    }

    public List<AdventureReviews> getAllReviewsByOnePenalty()
    {
        return adventureReviewsRepository.findAllReviewsByOnePenalty(false);
    }

    public Boolean strikeOnePenalty(Long id){
        Optional<AdventureReviews> review = adventureReviewsRepository.findById(id);

        if(review.isEmpty()){
            return false;
        }
        review.get().setPenalty(true);
        adventureReviewsRepository.save(review.get());
        return true;
    }




}
