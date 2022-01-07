package com.ISA.service.definition;

import com.ISA.domain.dto.SearchFreeHomesDTO;
import com.ISA.domain.model.HomeProfile;


import java.util.List;

public interface SearchFreeHomesService {

    List<HomeProfile> findAllFree(SearchFreeHomesDTO dto);
    Boolean homeExists(HomeProfile home, List<HomeProfile> homes);
}