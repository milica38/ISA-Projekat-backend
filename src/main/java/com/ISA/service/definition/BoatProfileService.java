package com.ISA.service.definition;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.model.BoatProfile;

import java.util.List;

public interface BoatProfileService {

    List<BoatProfile> getAll();
    BoatProfile get(Long id);
    BoatProfile add(BoatProfileDTO boatProfileDTO);
    BoatProfile edit(BoatProfileDTO boatProfileDTO);
    boolean delete(Long id);
    List<BoatProfile> getAllNotDeleted();
}
