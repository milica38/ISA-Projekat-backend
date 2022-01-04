package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.service.definition.BoatProfileService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatProfileServiceImpl implements BoatProfileService {

    @Autowired
    private BoatProfileRepository boatProfileRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<BoatProfile> getAll() { return boatProfileRepository.findAll(); }

    @Override
    public List<BoatProfile> getMyNotDeletedBoats() {
        User user = userService.getCurrentUser();

        return boatProfileRepository.findAllByOwnerIdAndDeleted(user.getId(), false);
    }

    @Override
    public List<BoatProfile> getAllNotDeleted() { return boatProfileRepository.findAllByDeleted(false); }

    @Override
    public BoatProfile get(Long id) { return boatProfileRepository.findById(id).get(); }

    @Override
    public BoatProfile add(BoatProfileDTO boatProfileDTO) {

        BoatProfile bp = new BoatProfile();

        bp.setOwnerId(userService.getCurrentUser().getId());
        bp.setExtraPrice(boatProfileDTO.getExtraPrice());
        bp.setAddress(boatProfileDTO.getAddress());
        bp.setExtraService(boatProfileDTO.getExtraService());
        bp.setBehaviourRules(boatProfileDTO.getBehaviourRules());
        bp.setExteriorImage(boatProfileDTO.getExteriorImage());
        bp.setInteriorImage(boatProfileDTO.getInteriorImage());
        bp.setCancelConditions(boatProfileDTO.getCancelConditions());
        bp.setCapacity(boatProfileDTO.getCapacity());
        bp.setPriceList(boatProfileDTO.getPricelist());
        bp.setLength(boatProfileDTO.getLength());
        bp.setEngineNumber(boatProfileDTO.getEngineNumber());
        bp.setEnginePower(boatProfileDTO.getEnginePower());
        bp.setFishingEquipment(boatProfileDTO.getFishingEquipment());
        bp.setPromoDescription(boatProfileDTO.getPromoDescription());
        bp.setMaxSpeed(boatProfileDTO.getMaxSpeed());
        bp.setName(boatProfileDTO.getName());
        bp.setType(boatProfileDTO.getType());
        return boatProfileRepository.save(bp);
    }

    public BoatProfile edit(Long id, BoatProfileDTO boatProfileDTO) {

        Optional<BoatProfile> optionalBoatProfile = boatProfileRepository.findById(boatProfileDTO.getId());

        optionalBoatProfile.get().setExtraPrice(boatProfileDTO.getExtraPrice());
        optionalBoatProfile.get().setAddress(boatProfileDTO.getAddress());
        optionalBoatProfile.get().setExtraService(boatProfileDTO.getExtraService());
        optionalBoatProfile.get().setBehaviourRules(boatProfileDTO.getBehaviourRules());
        optionalBoatProfile.get().setCancelConditions(boatProfileDTO.getCancelConditions());
        optionalBoatProfile.get().setCapacity(boatProfileDTO.getCapacity());
        optionalBoatProfile.get().setPriceList(boatProfileDTO.getPricelist());
        optionalBoatProfile.get().setLength(boatProfileDTO.getLength());
        optionalBoatProfile.get().setEngineNumber(boatProfileDTO.getEngineNumber());
        optionalBoatProfile.get().setEnginePower(boatProfileDTO.getEnginePower());
        optionalBoatProfile.get().setFishingEquipment(boatProfileDTO.getFishingEquipment());
        optionalBoatProfile.get().setPromoDescription(boatProfileDTO.getPromoDescription());
        optionalBoatProfile.get().setMaxSpeed(boatProfileDTO.getMaxSpeed());
        optionalBoatProfile.get().setType(boatProfileDTO.getType());
        optionalBoatProfile.get().setName(boatProfileDTO.getName());
        if(boatProfileDTO.getInteriorImage() != null && !boatProfileDTO.getInteriorImage().equals("")) {
            optionalBoatProfile.get().setInteriorImage(boatProfileDTO.getInteriorImage());
        }
        if(boatProfileDTO.getExteriorImage() != null && !boatProfileDTO.getExteriorImage().equals("")) {
            optionalBoatProfile.get().setExteriorImage(boatProfileDTO.getExteriorImage());
        }

        return boatProfileRepository.save(optionalBoatProfile.get());
    }

    @Override
    public boolean delete(Long id) {

        Optional<BoatProfile> optionalBoatProfile = boatProfileRepository.findById(id);
        optionalBoatProfile.get().setDeleted(true);
        boatProfileRepository.save(optionalBoatProfile.get());
        return true;
    }

    @Override
    public List<BoatProfile> getMyBoats() {
        User user = userService.getCurrentUser();

        return  boatProfileRepository.getAllByOwnerId(user.getId());
    }
}
