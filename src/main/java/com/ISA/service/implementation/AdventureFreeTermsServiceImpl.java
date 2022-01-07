package com.ISA.service.implementation;

import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.repository.AdventureFreeTermsRepository;
import com.ISA.service.definition.AdventureFreeTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureFreeTermsServiceImpl implements AdventureFreeTermsService {

    @Autowired
    private AdventureFreeTermsRepository freeTermsRepository;

    public List<AdventureFreeTerms> getAll(Long adventureId) {
        return freeTermsRepository.findAllByAdventureProfileId(adventureId);
    }
}
