package com.ISA.service.definition;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeProfile;

import java.util.List;

public interface HomeProfileService {

    List<HomeProfile> getAll();
    List<HomeProfile> getAllNotDeleted();
    HomeProfile get(Long id);
    HomeProfile add(HomeProfileDTO homeProfileDTO);
    HomeProfile edit(Long id, HomeProfileDTO homeProfileDTO);
    boolean delete(Long id);
    List<HomeProfile> getMyHouses();
    List<HomeProfile> getMyNotDeletedHouses();
    List<HomeProfile> filterHomes(HomeProfileDTO dto);
    boolean homeExists(HomeProfile home, List<HomeProfile> homes);
    boolean canOwnerDelete(Long houseId);
    boolean canOwnerEdit(Long houseId);


}
