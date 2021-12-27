package com.ISA.service.implementation;

import com.ISA.domain.dto.SearchFreeHomesDTO;
import com.ISA.service.definition.SearchFreeHomesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFreeHomesServiceImpl implements SearchFreeHomesService {

    @Override
    public List<SearchFreeHomesDTO> findAllFree() {
        return null;
    }
}
