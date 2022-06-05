package com.ISA.service.definition;

import com.ISA.domain.dto.HomeReviewsDTO;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.HomeReviews;

import java.util.List;

public interface HomeReviewsService {
    HomeReviews add(HomeReviewsDTO dto);
    List<HomeReviews> getAllHomeReviews();
    List<HomeReviews> getAllReviewsByOnePenalty();
    Boolean strikeOnePenalty(Long id);
    List<HomeReviews> getAllReviewsByOnePenaltyAndBadComment();
}
