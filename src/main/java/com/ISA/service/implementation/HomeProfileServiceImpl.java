package com.ISA.service.implementation;

import com.ISA.config.SecurityUtils;
import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.service.definition.HomeProfileService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HomeProfileServiceImpl implements HomeProfileService {

    @Autowired
    private HomeProfileRepository homeProfileRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<HomeProfile> getAll() {
        return homeProfileRepository.findAll();
    }

    @Override
    public List<HomeProfile> getMyNotDeletedHouses() {
        User user = userService.getCurrentUser();

        return homeProfileRepository.findAllByOwnerIdAndDeleted(user.getId(), false);
    }

    @Override
    public List<HomeProfile> getAllNotDeleted() {
        return homeProfileRepository.findAllByDeleted(false);
    }


    @Override
    public HomeProfile get(Long id) {
        return homeProfileRepository.findById(id).get();
    }

    @Override
    public HomeProfile add(HomeProfileDTO homeProfileDTO) {


        HomeProfile hp = new HomeProfile();

        hp.setOwnerId(userService.getCurrentUser().getId());
        hp.setName(homeProfileDTO.getName());
        hp.setAddress(homeProfileDTO.getAddress());
        hp.setExtraService(homeProfileDTO.getExtraService());
        hp.setBehaviourRules(homeProfileDTO.getBehaviourRules());
        hp.setExteriorImage(homeProfileDTO.getExteriorImage());
        hp.setInteriorImage(homeProfileDTO.getInteriorImage());
        hp.setPromoDescription(homeProfileDTO.getPromoDescription());
        hp.setNumberOfBeds(homeProfileDTO.getNumberOfBeds());
        hp.setNumberOfRooms(homeProfileDTO.getNumberOfRooms());
        hp.setPricelist(homeProfileDTO.getPricelist());
        return homeProfileRepository.save(hp);
    }

    @Override
    public HomeProfile edit(HomeProfileDTO homeProfileDTO) {

        Optional<HomeProfile> optionalHomeProfile = homeProfileRepository.findById(homeProfileDTO.getId());

        optionalHomeProfile.get().setId(homeProfileDTO.getId());
        optionalHomeProfile.get().setName(homeProfileDTO.getName());
        optionalHomeProfile.get().setAddress(homeProfileDTO.getAddress());
        optionalHomeProfile.get().setPricelist(homeProfileDTO.getPricelist());
        optionalHomeProfile.get().setNumberOfRooms(homeProfileDTO.getNumberOfRooms());
        optionalHomeProfile.get().setNumberOfBeds(homeProfileDTO.getNumberOfRooms());
        optionalHomeProfile.get().setPromoDescription(homeProfileDTO.getPromoDescription());
        optionalHomeProfile.get().setExtraService(homeProfileDTO.getExtraService());
        optionalHomeProfile.get().setInteriorImage(homeProfileDTO.getInteriorImage());
        optionalHomeProfile.get().setExteriorImage(homeProfileDTO.getExteriorImage());
        optionalHomeProfile.get().setBehaviourRules(homeProfileDTO.getBehaviourRules());
        optionalHomeProfile.get().setOwnerId(homeProfileDTO.getownerId());

        return homeProfileRepository.save(optionalHomeProfile.get());
    }

    @Override
    public boolean delete(Long id) {

        Optional<HomeProfile> optionalHomeProfile = homeProfileRepository.findById(id);
        optionalHomeProfile.get().setDeleted(true);
        homeProfileRepository.save(optionalHomeProfile.get());
        return true;
    }

    @Override
    public List<HomeProfile> getMyHouses() {
        User user = userService.getCurrentUser();

        return homeProfileRepository.getAllByOwnerId(user.getId());
    }

    @Override
    public List<HomeProfile> filterHomes(HomeProfileDTO dto) {

        List<HomeProfile> homes =  homeProfileRepository.findAll();
        List<HomeProfile> results = new ArrayList<>();

        for(HomeProfile home: homes){
            if(dto.getName().toLowerCase().equals(home.getName()))
            {
                if(!homeExists(home, results)){
                    results.add(home);
                }
            }
        }
        return results;
    }

    @Override
    public boolean homeExists(HomeProfile home, List<HomeProfile> homes) {

        for(HomeProfile profile: homes){
            if(profile.getId().equals(home.getId())){
                return true;
            }
        }
        return false;
    }


}
