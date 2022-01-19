package com.ISA.service.definition;

import com.ISA.domain.dto.BoatReviewsDTO;
import com.ISA.domain.model.BoatReviews;

public interface BoatReviewsService {
    BoatReviews add(BoatReviewsDTO dto);
}
