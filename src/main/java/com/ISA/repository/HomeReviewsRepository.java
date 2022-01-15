package com.ISA.repository;

import com.ISA.domain.model.HomeReviews;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HomeReviewsRepository extends JpaRepository<HomeReviews, Long> {
}
