package com.ISA.domain.dto.converters;

import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.model.AdventureProfile;

import java.util.ArrayList;
import java.util.List;

public class AdventureProfileConverters {

    public static AdventureProfileDTO modelToDTO(AdventureProfile adventureProfile) {
        AdventureProfileDTO dto = new AdventureProfileDTO();
        dto.setAddress(adventureProfile.getAddress());
        dto.setAmbientImage(adventureProfile.getAmbientImage());
        dto.setBehaviourRules(adventureProfile.getBehaviourRules());
        dto.setCancelConditions(adventureProfile.getCancelConditions());
        dto.setExtraService(adventureProfile.getExtraService());
        dto.setFishingEquipment(adventureProfile.getFishingEquipment());
        dto.setFreeTerm(adventureProfile.getFreeTerm());
        dto.setInstructorBiography(adventureProfile.getInstructorBiography());
        dto.setMaxNumberOfPeople(adventureProfile.getMaxNumberOfPeople());
        dto.setName(adventureProfile.getName());
        dto.setPriceList(adventureProfile.getPriceList());
        dto.setPromoDescription(adventureProfile.getPromoDescription());

        return dto;
    }

    public static List<AdventureProfileDTO> modelsToDTOs(List<AdventureProfile> adventures) {
        List<AdventureProfileDTO> result = new ArrayList<>();

        for(AdventureProfile ap: adventures){
            result.add(modelToDTO(ap));
        }
        return result;
    }

}
