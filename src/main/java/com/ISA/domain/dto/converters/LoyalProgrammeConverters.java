package com.ISA.domain.dto.converters;

import com.ISA.domain.dto.LoyalProgrammeDTO;
import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.model.LoyalProgramme;
import com.ISA.domain.model.LoyaltyProgramme;

import java.util.ArrayList;
import java.util.List;

public class LoyalProgrammeConverters {
    public static LoyalProgrammeDTO modelToDTO(LoyalProgramme loyalProgramme) {

        LoyalProgrammeDTO dto = new LoyalProgrammeDTO();
        dto.setSilverPoints(loyalProgramme.getSilverPoints());
        dto.setGoldPoints(loyalProgramme.getGoldPoints());
        dto.setSilverAction(loyalProgramme.getSilverAction());
        dto.setGoldAction(loyalProgramme.getGoldAction());
        dto.setReservationPoints(loyalProgramme.getReservationPoints());
        dto.setReservedPoints(loyalProgramme.getReservedPoints());
        dto.setId(loyalProgramme.getId());
        return dto;
    }

    public static List<LoyalProgrammeDTO> modelsToDTOs(List<LoyalProgramme> loyalties) {

        List<LoyalProgrammeDTO> result = new ArrayList<>();

        for(LoyalProgramme lp: loyalties) {
            result.add(modelToDTO(lp));
        }

        return result;
    }
}
