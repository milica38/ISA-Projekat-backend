package com.ISA.service.definition;

import com.ISA.domain.dto.BoatReviewsDTO;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.BoatReviews;

import java.util.List;

public interface BoatReviewsService {
    BoatReviews add(BoatReviewsDTO dto);
    List<BoatReviews> getAllBoatReviews();
    List<BoatReviews> getAllReviewsByOnePenalty();
    Boolean strikeOnePenalty(Long id);
}
