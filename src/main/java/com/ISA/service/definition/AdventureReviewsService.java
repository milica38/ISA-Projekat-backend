package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureReviewsDTO;
import com.ISA.domain.model.AdventureReviews;


public interface AdventureReviewsService {
    AdventureReviews add(AdventureReviewsDTO dto);
}
