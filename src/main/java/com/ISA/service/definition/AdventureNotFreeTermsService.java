package com.ISA.service.definition;


import com.ISA.domain.dto.AdventureFreeTermsDTO;
import com.ISA.domain.dto.AdventureNotFreeTermsDTO;
import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.domain.model.AdventureNotFreeTerms;

import java.util.List;

public interface AdventureNotFreeTermsService {

    List<AdventureNotFreeTerms> getAll(Long adventureId);
    AdventureNotFreeTerms add(AdventureNotFreeTermsDTO AdventureNotFreeTermsDTO);
}
