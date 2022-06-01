package com.ISA.service.definition;

import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.model.LoyaltyProgramme;

public interface LoyaltyProgrammeService {
    LoyaltyProgramme add(LoyaltyProgrammeDTO loyaltyProgrammeDTO);
    LoyaltyProgramme edit(Long id, LoyaltyProgrammeDTO loyaltyProgrammeDTO);
    boolean delete(Long id);
}
