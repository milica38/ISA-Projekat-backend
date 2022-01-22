package com.ISA.repository;

import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.HomeReviews;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HomeReviewsRepository extends JpaRepository<HomeReviews, Long> {
    List<HomeReviews> findAllReviewsByOnePenalty(boolean onePenalty);
}
