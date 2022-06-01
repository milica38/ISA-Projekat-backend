package com.ISA.domain.dto.converters;

import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.model.LoyaltyProgramme;

import java.util.ArrayList;
import java.util.List;

public class LoyaltyProgrammeConverters {
    public static LoyaltyProgrammeDTO modelToDTO(LoyaltyProgramme loyaltyProgramme) {

        LoyaltyProgrammeDTO dto = new LoyaltyProgrammeDTO();
        dto.setNumberOfAllReservations(loyaltyProgramme.getNumberOfAllReservations());
        dto.setNumberOfBoatReservations(loyaltyProgramme.getNumberOfBoatReservations());
        dto.setNumberOfHouseReservations(loyaltyProgramme.getNumberOfHouseReservations());
        dto.setNumberOfInstructorReservations(loyaltyProgramme.getNumberOfInstructorReservations());
        dto.setAction(loyaltyProgramme.getAction());
        dto.setLoyaltyId(loyaltyProgramme.getLoyaltyId());
        return dto;
    }

    public static List<LoyaltyProgrammeDTO> modelsToDTOs(List<LoyaltyProgramme> loyalties) {

        List<LoyaltyProgrammeDTO> result = new ArrayList<>();

        for(LoyaltyProgramme lp: loyalties) {
            result.add(modelToDTO(lp));
        }

        return result;
    }
}
