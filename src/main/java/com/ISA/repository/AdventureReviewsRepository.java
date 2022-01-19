package com.ISA.repository;

import com.ISA.domain.model.AdventureReviews;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdventureReviewsRepository extends JpaRepository<AdventureReviews, Long> {
}
