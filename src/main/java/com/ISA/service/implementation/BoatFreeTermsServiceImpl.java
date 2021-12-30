package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatFreeTermsDTO;
import com.ISA.domain.model.BoatFreeTerms;
import com.ISA.domain.model.BoatProfile;
import com.ISA.repository.BoatFreeTermsRepository;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.service.definition.BoatFreeTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatFreeTermsServiceImpl implements BoatFreeTermsService {

    @Autowired
    private BoatFreeTermsRepository boatFreeTermsRepository;

    @Autowired
    private BoatProfileRepository boatProfileRepository;

    public List<BoatFreeTerms> getAll(Long boatId) {

        return  boatFreeTermsRepository.findAllByBoatProfileId(boatId);
    }

    @Override
    public BoatFreeTerms add(BoatFreeTermsDTO boatFreeTermsDTO) {

        BoatFreeTerms boatFreeTerms = new BoatFreeTerms();
        BoatProfile boatProfile = boatProfileRepository.findById(boatFreeTermsDTO.getBoatId()).get();

        boatFreeTerms.setStartDate(boatFreeTermsDTO.getStartDate());
        boatFreeTerms.setEndDate(boatFreeTermsDTO.getEndDate());
        boatFreeTerms.setBoatProfile(boatProfile);

        return boatFreeTermsRepository.save(boatFreeTerms);
    }
}
