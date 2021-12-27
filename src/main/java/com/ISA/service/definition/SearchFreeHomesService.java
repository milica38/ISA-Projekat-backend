package com.ISA.service.definition;

import com.ISA.domain.dto.SearchFreeHomesDTO;

import java.util.List;

public interface SearchFreeHomesService {

    List<SearchFreeHomesDTO> findAllFree();
}
