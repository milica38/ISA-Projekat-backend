package com.ISA.domain.dto.converters;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.HomeProfile;

import java.util.ArrayList;
import java.util.List;

public class BoatProfileConverters {

    public static BoatProfileDTO modelToDTO(BoatProfile boatProfile) {
        BoatProfileDTO dto = new BoatProfileDTO();
        dto.setAddress(boatProfile.getAddress());
        dto.setBehaviourRules(boatProfile.getBehaviourRules());
        dto.setCancelConditions(boatProfile.getCancelConditions());
        dto.setCapacity(boatProfile.getCapacity());
        dto.setExteriorImage(boatProfile.getExteriorImage());
        dto.setExtraService(boatProfile.getExtraService());
        dto.setName(boatProfile.getName());
        dto.setInteriorImage(boatProfile.getInteriorImage());
        dto.setId(boatProfile.getId());
        dto.setPromoDescription(boatProfile.getPromoDescription());
        dto.setEngineNumber(boatProfile.getEngineNumber());
        dto.setEnginePower(boatProfile.getEnginePower());
        dto.setFishingEquipment(boatProfile.getFishingEquipment());
        dto.setLength(boatProfile.getLength());
        dto.setMaxSpeed(boatProfile.getMaxSpeed());
        dto.setNavEquipment(boatProfile.getNavEquipment());
        dto.setType(boatProfile.getType());
        dto.setExtraPrice(boatProfile.getExtraPrice());
        dto.setOwnerId(boatProfile.getownerId());
        dto.setPriceList(boatProfile.getPricelist());
        return dto;
    }

    public static List<BoatProfileDTO> modelsToDTOs(List<BoatProfile> boats) {

        List<BoatProfileDTO> result = new ArrayList<>();

        for(BoatProfile bp: boats) {
            result.add(modelToDTO(bp));
        }

        return result;
    }
}
