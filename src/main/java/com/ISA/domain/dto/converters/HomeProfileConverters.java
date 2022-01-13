package com.ISA.domain.dto.converters;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeProfile;

import java.util.ArrayList;
import java.util.List;

public class HomeProfileConverters {

    public static HomeProfileDTO modelToDTO(HomeProfile homeProfile) {

        HomeProfileDTO dto = new HomeProfileDTO();
        dto.setAddress(homeProfile.getAddress());
        dto.setBehaviourRules(homeProfile.getBehaviourRules());
        dto.setExteriorImage(homeProfile.getExteriorImage());
        dto.setExtraService(homeProfile.getExtraService());
        dto.setName(homeProfile.getName());
        dto.setInteriorImage(homeProfile.getInteriorImage());
        dto.setNumberOfBeds(homeProfile.getNumberOfBeds());
        dto.setNumberOfRooms(homeProfile.getNumberOfRooms());
        dto.setId(homeProfile.getId());
        dto.setOwnerId(homeProfile.getownerId());
        dto.setPricelist(homeProfile.getPricelist());
        dto.setPromoDescription(homeProfile.getPromoDescription());
        dto.setExtraPrice(homeProfile.getExtraPrice());
        dto.setLatitude(homeProfile.getLatitude());
        dto.setLongitude(homeProfile.getLongitude());
        return dto;
    }

    public static List<HomeProfileDTO> modelsToDTOs(List<HomeProfile> homes) {

        List<HomeProfileDTO> result = new ArrayList<>();

        for(HomeProfile hp: homes) {
            result.add(modelToDTO(hp));
        }

        return result;
    }
}
