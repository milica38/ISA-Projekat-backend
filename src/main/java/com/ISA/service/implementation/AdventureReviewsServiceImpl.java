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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
         User client = userService.getUserById(reservation.getClientId() );

        AdventureReviews reviews = new AdventureReviews();
        reviews.setContent(dto.getContent());
        reviews.setInstructorId(currentUser.getId());
        reviews.setAppear(dto.isAppear());
        reviews.setBadComment(dto.isBadComment());
        reviews.setAdventureReservation(reservation);

        if(reviews.isAppear()==true){
            reviews.setPenalty(true);
            client.setPenalty(+1L);
        }
        return adventureReviewsRepository.save(reviews);
    }

    public List<AdventureReviews> getAllAdventureReviews() {

        return adventureReviewsRepository.findAll();

    }

    public List<AdventureReviews> getAllReviewsByOnePenalty()
    {
        return adventureReviewsRepository.findAllReviewsByOnePenalty(false);
    }

    public List<AdventureReviews> getAllReviewsByOnePenaltyAndBadComment()
    {
        return adventureReviewsRepository.findAllReviewsByOnePenaltyAndIsBadComment(false,true);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean strikeOnePenalty(Long id){

        Optional<AdventureReviews> review = adventureReviewsRepository.findById(id);
        User user = userService.getUserById(review.get().getAdventureReservation().getClientId() );
        User instructor = userService.getUserById(review.get().getInstructorId() );
        if(review.isEmpty()){
            return false;
        }
        review.get().setPenalty(true);
        user.setPenalty(user.getPenalty()+1);

        emailService.sendEmailForPenaltyClient(user);
        emailService.sendEmailForPenaltyOwnerOrInstructor(instructor);
        adventureReviewsRepository.save(review.get());
        return true;
    }




}
