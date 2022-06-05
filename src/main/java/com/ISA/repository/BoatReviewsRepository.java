package com.ISA.repository;

import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.BoatReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatReviewsRepository extends JpaRepository<BoatReviews, Long> {
    List<BoatReviews> findAllReviewsByOnePenalty(boolean onePenalty);
    List<BoatReviews> findAllReviewsByOnePenaltyAndIsBadComment(boolean onePenalty, boolean isBadComment);
}
