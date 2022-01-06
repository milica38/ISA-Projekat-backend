package com.ISA.service.definition;

import com.ISA.domain.model.AdventureFreeTerms;

import java.util.List;

public interface AdventureFreeTermsService {

    List<AdventureFreeTerms> getAll(Long adventureId);
}
