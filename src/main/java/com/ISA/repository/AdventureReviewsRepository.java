package com.ISA.repository;

import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.Subscriptions;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface AdventureReviewsRepository extends JpaRepository<AdventureReviews, Long> {
    List<AdventureReviews> findAllReviewsByOnePenalty(boolean onePenalty);
    List<AdventureReviews> findAllReviewsByOnePenaltyAndIsBadComment(boolean onePenalty, boolean isBadComment);
}
