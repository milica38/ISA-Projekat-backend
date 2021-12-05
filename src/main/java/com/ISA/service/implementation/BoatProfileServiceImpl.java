package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.HomeProfile;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.service.definition.BoatProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatProfileServiceImpl implements BoatProfileService {

    @Autowired
    private BoatProfileRepository boatProfileRepository;

    @Override
    public List<BoatProfile> getAll() { return boatProfileRepository.findAll(); }

    @Override
    public BoatProfile get(Long id) { return boatProfileRepository.findById(id).get(); }

    @Override
    public BoatProfile add(BoatProfileDTO boatProfileDTO) {

        BoatProfile bp = new BoatProfile();

        bp.setAddress(boatProfileDTO.getAddress());
        bp.setExtraService(boatProfileDTO.getExtraService());
        bp.setBehaviourRules(boatProfileDTO.getBehaviourRules());
        bp.setExteriorImage(boatProfileDTO.getExteriorImage());
        bp.setInteriorImage(boatProfileDTO.getInteriorImage());
        bp.setCancelConditions(boatProfileDTO.getCancelConditions());
        bp.setCapacity(boatProfileDTO.getCapacity());
        bp.setLatitude(boatProfileDTO.getLatitude());
        bp.setLongitude(boatProfileDTO.getLongitude());
        bp.setPriceList(boatProfileDTO.getPricelist());
        bp.setLength(boatProfileDTO.getLength());
        bp.setEngineNumber(boatProfileDTO.getEngineNumber());
        bp.setEnginePower(boatProfileDTO.getEnginePower());
        bp.setFreeTerms(boatProfileDTO.getFreeTerms());
        bp.setFishingEquipment(boatProfileDTO.getFishingEquipment());
        bp.setPromoDescription(boatProfileDTO.getPromoDescription());
        bp.setPriceList(boatProfileDTO.getPricelist());
        bp.setMaxSpeed(boatProfileDTO.getMaxSpeed());
        bp.setId(boatProfileDTO.getId());
        bp.setName(boatProfileDTO.getName());
        bp.setType(boatProfileDTO.getType());

        return boatProfileRepository.save(bp);
    }

    @Override
    public BoatProfile edit(BoatProfileDTO boatProfileDTO) {

        Optional<BoatProfile> optionalBoatProfile = boatProfileRepository.findById(boatProfileDTO.getId());

        optionalBoatProfile.get().setAddress(boatProfileDTO.getAddress());
        optionalBoatProfile.get().setExtraService(boatProfileDTO.getExtraService());
        optionalBoatProfile.get().setInteriorImage(boatProfileDTO.getInteriorImage());
        optionalBoatProfile.get().setExteriorImage(boatProfileDTO.getExteriorImage());
        optionalBoatProfile.get().setBehaviourRules(boatProfileDTO.getBehaviourRules());
        optionalBoatProfile.get().setCancelConditions(boatProfileDTO.getCancelConditions());
        optionalBoatProfile.get().setCapacity(boatProfileDTO.getCapacity());
        optionalBoatProfile.get().setLatitude(boatProfileDTO.getLatitude());
        optionalBoatProfile.get().setLongitude(boatProfileDTO.getLongitude());
        optionalBoatProfile.get().setPriceList(boatProfileDTO.getPricelist());
        optionalBoatProfile.get().setLength(boatProfileDTO.getLength());
        optionalBoatProfile.get().setEngineNumber(boatProfileDTO.getEngineNumber());
        optionalBoatProfile.get().setEnginePower(boatProfileDTO.getEnginePower());
        optionalBoatProfile.get().setFreeTerms(boatProfileDTO.getFreeTerms());
        optionalBoatProfile.get().setFishingEquipment(boatProfileDTO.getFishingEquipment());
        optionalBoatProfile.get().setPromoDescription(boatProfileDTO.getPromoDescription());
        optionalBoatProfile.get().setPriceList(boatProfileDTO.getPricelist());
        optionalBoatProfile.get().setMaxSpeed(boatProfileDTO.getMaxSpeed());
        optionalBoatProfile.get().setId(boatProfileDTO.getId());
        optionalBoatProfile.get().setType(boatProfileDTO.getType());
        optionalBoatProfile.get().setName(boatProfileDTO.getName());

        return boatProfileRepository.save(optionalBoatProfile.get());
    }

    @Override
    public boolean delete(Long id) {

        Optional<BoatProfile> optionalBoatProfile = boatProfileRepository.findById(id);
        optionalBoatProfile.get().setDeleted(true);
        boatProfileRepository.save(optionalBoatProfile.get());
        return true;
    }
}
