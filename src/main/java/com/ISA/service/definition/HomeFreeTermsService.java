package com.ISA.service.definition;

import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.model.HomeFreeTerms;

import java.util.List;

public interface HomeFreeTermsService {

    List<HomeFreeTerms> getAll(Long houseId);
    HomeFreeTerms add(HomeFreeTermsDTO homeFreeTermsDTO);
    List<HomeFreeTerms>  getAllActions();

}
