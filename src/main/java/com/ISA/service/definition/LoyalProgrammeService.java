package com.ISA.service.definition;

import com.ISA.domain.dto.LoyalProgrammeDTO;
import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.LoyalProgramme;
import com.ISA.domain.model.LoyaltyProgramme;

import java.util.List;

public interface LoyalProgrammeService {
    LoyalProgramme add(LoyalProgrammeDTO loyalProgrammeDTO);
    LoyalProgramme edit(int id, LoyalProgrammeDTO loyalProgrammeDTO);
    boolean delete(int id);
    LoyalProgramme get(int id);
    List<LoyalProgramme> getAll();
}
