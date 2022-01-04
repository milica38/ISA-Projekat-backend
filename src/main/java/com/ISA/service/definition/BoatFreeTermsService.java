package com.ISA.service.definition;

import com.ISA.domain.dto.BoatFreeTermsDTO;
import com.ISA.domain.model.BoatFreeTerms;

import java.util.List;

public interface BoatFreeTermsService {

    List<BoatFreeTerms> getAll(Long boatId);
    BoatFreeTerms add(BoatFreeTermsDTO BoatFreeTermsDTO);
}
