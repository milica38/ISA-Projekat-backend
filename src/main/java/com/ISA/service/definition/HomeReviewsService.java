package com.ISA.service.definition;

import com.ISA.domain.dto.HomeReviewsDTO;
import com.ISA.domain.model.HomeReviews;

public interface HomeReviewsService {
    HomeReviews add(HomeReviewsDTO dto);
}
