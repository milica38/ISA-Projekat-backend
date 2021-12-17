package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.model.AdventureProfile;

import java.util.ArrayList;
import java.util.List;

public interface AdventureProfileService {

    List<AdventureProfile> getAll();
    AdventureProfile get(Long id);
    AdventureProfile add(AdventureProfileDTO adventureProfileDTO);
    AdventureProfile edit(AdventureProfileDTO adventureProfileDTO);
    boolean delete(Long id);

}
