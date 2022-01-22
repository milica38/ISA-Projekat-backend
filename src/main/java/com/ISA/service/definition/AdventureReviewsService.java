package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureReviewsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.AdventureReviews;

import java.util.List;


public interface AdventureReviewsService {
    AdventureReviews add(AdventureReviewsDTO dto);
    List<AdventureReviews> getAllAdventureReviews();
    List<AdventureReviews> getAllReviewsByOnePenalty();
    Boolean strikeOnePenalty(Long id);
}
