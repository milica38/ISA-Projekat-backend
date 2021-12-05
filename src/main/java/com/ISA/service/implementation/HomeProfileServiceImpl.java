package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.service.definition.HomeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeProfileServiceImpl implements HomeProfileService {

    @Autowired
    private HomeProfileRepository homeProfileRepository;

    @Override
    public List<HomeProfile> getAll() {
        return homeProfileRepository.findAll();
    }

    @Override
    public HomeProfile get(Long id) {
        return homeProfileRepository.findById(id).get();
    }

    @Override
    public HomeProfile add(HomeProfileDTO homeProfileDTO) {

        HomeProfile hp = new HomeProfile();

        hp.setId(homeProfileDTO.getId());
        hp.setName(homeProfileDTO.getName());
        hp.setAddress(homeProfileDTO.getAddress());
        hp.setLatitude(homeProfileDTO.getLatitude());
        hp.setLongitude(homeProfileDTO.getLongitude());
        hp.setExtraService(homeProfileDTO.getExtraService());
        hp.setBehaviourRules(homeProfileDTO.getBehaviourRules());
        hp.setExteriorImage(homeProfileDTO.getExteriorImage());
        hp.setInteriorImage(homeProfileDTO.getInteriorImage());
        hp.setFreeTerms(homeProfileDTO.getFreeTerms());
        hp.setPromoDescription(homeProfileDTO.getPromoDescription());
        hp.setNumberOfBeds(homeProfileDTO.getNumberOfBeds());
        hp.setNumberOfRooms(homeProfileDTO.getNumberOfRooms());
        hp.setPriceList(homeProfileDTO.getPricelist());

        return homeProfileRepository.save(hp);
    }

    @Override
    public HomeProfile edit(HomeProfileDTO homeProfileDTO) {

        Optional<HomeProfile> optionalHomeProfile = homeProfileRepository.findById(homeProfileDTO.getId());

        optionalHomeProfile.get().setId(homeProfileDTO.getId());
        optionalHomeProfile.get().setName(homeProfileDTO.getName());
        optionalHomeProfile.get().setAddress(homeProfileDTO.getAddress());
        optionalHomeProfile.get().setPriceList(homeProfileDTO.getPricelist());
        optionalHomeProfile.get().setNumberOfRooms(homeProfileDTO.getNumberOfRooms());
        optionalHomeProfile.get().setNumberOfBeds(homeProfileDTO.getNumberOfRooms());
        optionalHomeProfile.get().setPromoDescription(homeProfileDTO.getPromoDescription());
        optionalHomeProfile.get().setExtraService(homeProfileDTO.getExtraService());
        optionalHomeProfile.get().setFreeTerms(homeProfileDTO.getFreeTerms());
        optionalHomeProfile.get().setInteriorImage(homeProfileDTO.getInteriorImage());
        optionalHomeProfile.get().setExteriorImage(homeProfileDTO.getExteriorImage());
        optionalHomeProfile.get().setBehaviourRules(homeProfileDTO.getBehaviourRules());
        optionalHomeProfile.get().setLatitude(homeProfileDTO.getLatitude());
        optionalHomeProfile.get().setLongitude(homeProfileDTO.getLongitude());

        return homeProfileRepository.save(optionalHomeProfile.get());
    }

    @Override
    public boolean delete(Long id) {

        Optional<HomeProfile> optionalHomeProfile = homeProfileRepository.findById(id);
        optionalHomeProfile.get().setDeleted(true);
        homeProfileRepository.save(optionalHomeProfile.get());
        return true;
    }
}
