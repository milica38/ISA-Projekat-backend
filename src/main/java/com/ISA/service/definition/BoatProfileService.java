package com.ISA.service.definition;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.HomeProfile;

import java.util.List;

public interface BoatProfileService {

    List<BoatProfile> getAll();
    List<BoatProfile> getAllNotDeleted();
    BoatProfile get(Long id);
    BoatProfile add(BoatProfileDTO boatProfileDTO);
    BoatProfile edit(Long id, BoatProfileDTO boatProfileDTO);
    boolean delete(Long id);
    List<BoatProfile> getMyNotDeletedBoats();
    List<BoatProfile> filterBoats(BoatProfileDTO dto);
    boolean boatsExists(BoatProfile boat, List<BoatProfile> boats);
    boolean canOwnerEdit(Long boatId);
    boolean canOwnerDelete(Long boatId);
}
