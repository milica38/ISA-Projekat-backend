package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatReviewsDTO;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.BoatReservation;
import com.ISA.domain.model.BoatReviews;
import com.ISA.domain.model.HomeReviews;
import com.ISA.domain.model.User;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.repository.BoatReviewsRepository;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.BoatReviewsService;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;


@Service
public class BoatReviewsServiceImpl implements BoatReviewsService {

    @Autowired
    BoatReviewsRepository boatReviewsRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoatReservationRepository boatReservationRepository;

    @Autowired
    EmailService emailService;

    @Override
    public BoatReviews add(BoatReviewsDTO dto) {

        BoatReservation reservation = boatReservationRepository.findById(dto.getBoatReservationId()).get();
        User currentUser = userService.getCurrentUser();
        List<BoatReviews> writedReviews = boatReviewsRepository.findAll();

        for(BoatReviews b: writedReviews){
            if(b.getBoatReservation().getCancelled() || b.getBoatReservation().getWrited()){
                return null;
            }
        }
        BoatReviews reviews = new BoatReviews();
        reviews.setContent(dto.getContent());
        reviews.setOwnerId(currentUser.getId());
        reviews.setAppear(dto.isAppear());
        reviews.setBadComment(dto.isBadComment());
        reviews.setBoatReservation(reservation);
        reviews.getBoatReservation().setWrited(true);

        if(dto.isAppear()) {
            currentUser.setPenalty(currentUser.getPenalty() + 1);
            userRepository.save(currentUser);
        }

        if(dto.isBadComment()) {
            currentUser.setPenalty(currentUser.getPenalty() + 1);
            userRepository.save(currentUser);
        }

        return boatReviewsRepository.save(reviews);
    }

    public List<BoatReviews> getAllBoatReviews() {

        return boatReviewsRepository.findAll();

    }

    public List<BoatReviews> getAllReviewsByOnePenalty()
    {
        return boatReviewsRepository.findAllReviewsByOnePenalty(false);
    }

    public List<BoatReviews> getAllReviewsByOnePenaltyAndBadComment()
    {
        return boatReviewsRepository.findAllReviewsByOnePenaltyAndIsBadComment(false,true);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean strikeOnePenalty(Long id){
        Optional<BoatReviews> review = boatReviewsRepository.findById(id);
        User user = userService.getUserById(review.get().getBoatReservation().getClientId() );
        User owner = userService.getUserById(review.get().getOwnerId() );
        if(review.isEmpty()){
            return false;
        }
        review.get().setPenalty(true);
        user.setPenalty(user.getPenalty()+1);
        emailService.sendEmailForPenaltyClient(user);
        emailService.sendEmailForPenaltyOwnerOrInstructor(owner);
        boatReviewsRepository.save(review.get());
        return true;
    }
}
