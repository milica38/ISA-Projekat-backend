package com.ISA.service.definition;

import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;

import java.util.List;

public interface HomeFreeTermsService {

    List<HomeFreeTerms> getAll(Long houseId);
    HomeFreeTerms add(HomeFreeTermsDTO homeFreeTermsDTO);
}
