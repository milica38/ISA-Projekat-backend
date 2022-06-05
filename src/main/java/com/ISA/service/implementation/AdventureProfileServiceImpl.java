package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.service.definition.AdventureProfileService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdventureProfileServiceImpl implements AdventureProfileService {

    @Autowired
    private AdventureProfileRepository adventureProfileRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<AdventureProfile> getAll() {
        return adventureProfileRepository.findAll();
    }

    @Override
    public List<AdventureProfile> getAllNotDeleted() {
        return adventureProfileRepository.findAllByDeleted(false);
    }

    @Override
    public AdventureProfile get(Long id) {
        return adventureProfileRepository.findById(id).get();
    }

    @Override
    public AdventureProfile add(AdventureProfileDTO adventureProfileDTO) {

        AdventureProfile ap = new AdventureProfile();
        ap.setId(adventureProfileDTO.getId());
        ap.setInstructorId(userService.getCurrentUser().getId());
        ap.setName(adventureProfileDTO.getName());
        ap.setAddress(adventureProfileDTO.getAddress());
        ap.setLatitude(adventureProfileDTO.getLatitude());
        ap.setLongitude(adventureProfileDTO.getLongitude());
        ap.setPromoDescription(adventureProfileDTO.getPromoDescription());
        ap.setInstructorBiography(adventureProfileDTO.getInstructorBiography());
        ap.setAmbientImage(adventureProfileDTO.getAmbientImage());
        ap.setMaxNumberOfPeople(adventureProfileDTO.getMaxNumberOfPeople());
        ap.setBehaviourRules(adventureProfileDTO.getBehaviourRules());
        ap.setFishingEquipment(adventureProfileDTO.getFishingEquipment());
        ap.setPriceList(adventureProfileDTO.getPricelist());
        ap.setExtraService(adventureProfileDTO.getExtraService());
        ap.setCancelConditions(adventureProfileDTO.getCancelConditions());


        return adventureProfileRepository.save(ap);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)

    public AdventureProfile edit(AdventureProfileDTO adventureProfileDTO) {

        Optional<AdventureProfile> optionalAdventureProfile = adventureProfileRepository.findById(adventureProfileDTO.getId());

        optionalAdventureProfile.get().setId(adventureProfileDTO.getId());
        optionalAdventureProfile.get().setName(adventureProfileDTO.getName());
        optionalAdventureProfile.get().setAddress(adventureProfileDTO.getAddress());
        optionalAdventureProfile.get().setLatitude(adventureProfileDTO.getLatitude());
        optionalAdventureProfile.get().setLongitude(adventureProfileDTO.getLongitude());
        optionalAdventureProfile.get().setPromoDescription(adventureProfileDTO.getPromoDescription());
        optionalAdventureProfile.get().setInstructorBiography(adventureProfileDTO.getInstructorBiography());
        optionalAdventureProfile.get().setAmbientImage(adventureProfileDTO.getAmbientImage());
        optionalAdventureProfile.get().setMaxNumberOfPeople(adventureProfileDTO.getMaxNumberOfPeople());
        optionalAdventureProfile.get().setBehaviourRules(adventureProfileDTO.getBehaviourRules());
        optionalAdventureProfile.get().setFishingEquipment(adventureProfileDTO.getFishingEquipment());
        optionalAdventureProfile.get().setPriceList(adventureProfileDTO.getPricelist());
        optionalAdventureProfile.get().setExtraService(adventureProfileDTO.getExtraService());
        optionalAdventureProfile.get().setCancelConditions(adventureProfileDTO.getCancelConditions());
        if(adventureProfileDTO.getInstructorId() != null) {
            optionalAdventureProfile.get().setInstructorId(adventureProfileDTO.getInstructorId());
        }

        if(adventureProfileDTO.getAmbientImage() != null && !adventureProfileDTO.getAmbientImage().equals("")) {
            optionalAdventureProfile.get().setAmbientImage(adventureProfileDTO.getAmbientImage());
        }


        return adventureProfileRepository.save(optionalAdventureProfile.get());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean delete(Long id) {
        Optional<AdventureProfile> optionalAdventureProfile = adventureProfileRepository.findById(id);
        optionalAdventureProfile.get().setDeleted(true);
        adventureProfileRepository.save(optionalAdventureProfile.get());
        return true;
    }


    public List<AdventureProfile> getMyNotDeletedAdventures() {
        User user = userService.getCurrentUser();

        return adventureProfileRepository.findAllByInstructorIdAndDeleted(user.getId(), false);
    }

    @Override
    public List<AdventureProfile> filterAdventures(AdventureProfileDTO dto) {

        List<AdventureProfile> results = new ArrayList<>();
        List<AdventureProfile> adventures = adventureProfileRepository.findAllByDeleted(false);

        for(AdventureProfile profile: adventures){
            if(profile.getName().toLowerCase().contains(dto.getSearchTerm().toLowerCase()) || profile.getAddress().toLowerCase().contains(dto.getSearchTerm().toLowerCase())
            || profile.getExtraService().toLowerCase().contains(dto.getSearchTerm().toLowerCase()) || profile.getFishingEquipment().toLowerCase().contains(dto.getSearchTerm().toLowerCase())
            || profile.getCancelConditions().toLowerCase().contains(dto.getSearchTerm().toLowerCase()) || profile.getInstructorBiography().toLowerCase().contains(dto.getSearchTerm().toLowerCase())){

                if (!adventureExists(profile, results)) {
                    results.add(profile);
                }
            }
        }
        return results;
    }

    @Override
    public boolean adventureExists(AdventureProfile profile, List<AdventureProfile> profiles) {
        for(AdventureProfile adventure: profiles){
            if(adventure.getId().equals(profile.getId()))
                return true;
        }
        return false;
    }
}
