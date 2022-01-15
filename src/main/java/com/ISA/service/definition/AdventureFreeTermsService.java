package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureFreeTermsDTO;
import com.ISA.domain.dto.BoatFreeTermsDTO;
import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.domain.model.BoatFreeTerms;

import java.util.List;

public interface AdventureFreeTermsService {

    List<AdventureFreeTerms> getAll(Long adventureId);
    AdventureFreeTerms add(AdventureFreeTermsDTO AdventureFreeTermsDTO);
}
