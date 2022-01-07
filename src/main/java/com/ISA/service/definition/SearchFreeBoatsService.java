package com.ISA.service.definition;

import com.ISA.domain.dto.SearchFreeBoatsDTO;
import com.ISA.domain.model.BoatProfile;

import java.util.List;

public interface SearchFreeBoatsService {

    List<BoatProfile> findAllFree(SearchFreeBoatsDTO dto);
    Boolean boatExists(BoatProfile boat, List<BoatProfile> boats);
}
