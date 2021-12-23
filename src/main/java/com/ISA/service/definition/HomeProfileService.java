package com.ISA.service.definition;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeProfile;

import java.util.List;

public interface HomeProfileService {

    List<HomeProfile> getAll();
    List<HomeProfile> getAllNotDeleted();
    HomeProfile get(Long id);
    HomeProfile add(HomeProfileDTO homeProfileDTO);
    HomeProfile edit(HomeProfileDTO homeProfileDTO);
    boolean delete(Long id);
    List<HomeProfile> getMyHouses();
}
