package com.ISA.service.definition;

import com.ISA.domain.dto.SearchFreeAdventuresDTO;
import com.ISA.domain.model.AdventureProfile;

import java.util.List;

public interface SearchFreeAdventuresService {

    List<AdventureProfile> findAllFree(SearchFreeAdventuresDTO dto);
    Boolean adventureExists(AdventureProfile adventure, List<AdventureProfile> adventures);
}
