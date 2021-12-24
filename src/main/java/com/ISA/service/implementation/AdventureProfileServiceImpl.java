package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.service.definition.AdventureProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdventureProfileServiceImpl implements AdventureProfileService {

    @Autowired
    private AdventureProfileRepository adventureProfileRepository;


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
        ap.setName(adventureProfileDTO.getName());
        ap.setAddress(adventureProfileDTO.getAddress());
        ap.setLatitude(adventureProfileDTO.getLatitude());
        ap.setLongitude(adventureProfileDTO.getLongitude());
        ap.setPromoDescription(adventureProfileDTO.getPromoDescription());
        ap.setInstructorBiography(adventureProfileDTO.getInstructorBiography());
        ap.setAmbientImage(adventureProfileDTO.getAmbientImage());
        ap.setMaxNumberOfPeople(adventureProfileDTO.getMaxNumberOfPeople());
        ap.setFreeTerm(adventureProfileDTO.getFreeTerm());
        ap.setBehaviourRules(adventureProfileDTO.getBehaviourRules());
        ap.setFishingEquipment(adventureProfileDTO.getFishingEquipment());
        ap.setPriceList(adventureProfileDTO.getPriceList());
        ap.setExtraService(adventureProfileDTO.getExtraService());
        ap.setCancelConditions(adventureProfileDTO.getCancelConditions());



        return adventureProfileRepository.save(ap);
    }

    @Override
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
        optionalAdventureProfile.get().setFreeTerm(adventureProfileDTO.getFreeTerm());
        optionalAdventureProfile.get().setBehaviourRules(adventureProfileDTO.getBehaviourRules());
        optionalAdventureProfile.get().setFishingEquipment(adventureProfileDTO.getFishingEquipment());
        optionalAdventureProfile.get().setPriceList(adventureProfileDTO.getPriceList());
        optionalAdventureProfile.get().setExtraService(adventureProfileDTO.getExtraService());
        optionalAdventureProfile.get().setCancelConditions(adventureProfileDTO.getCancelConditions());

        return adventureProfileRepository.save(optionalAdventureProfile.get());
    }

    @Override
    public boolean delete(Long id) {
        Optional<AdventureProfile> optionalAdventureProfile = adventureProfileRepository.findById(id);
        optionalAdventureProfile.get().setDeleted(true);
        adventureProfileRepository.save(optionalAdventureProfile.get());
        return true;
    }
}
