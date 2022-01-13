package com.ISA.service.implementation;

import com.ISA.config.SecurityUtils;
import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.repository.HomeReservationRepository;
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
    private HomeReservationRepository reservationRepository;

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

        hp.setExtraPrice(homeProfileDTO.getExtraPrice());
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
        hp.setLongitude(homeProfileDTO.getLongitude());
        hp.setLatitude(homeProfileDTO.getLatitude());

        return homeProfileRepository.save(hp);
    }

    public HomeProfile edit(Long id, HomeProfileDTO homeProfileDTO) {

        Optional<HomeProfile> optionalHomeProfile = homeProfileRepository.findById(id);

        if(!canOwnerDelete(id)){
            return null;
        }
        optionalHomeProfile.get().setLatitude(homeProfileDTO.getLatitude());
        optionalHomeProfile.get().setLongitude(homeProfileDTO.getLongitude());
        optionalHomeProfile.get().setExtraPrice(homeProfileDTO.getExtraPrice());
        optionalHomeProfile.get().setName(homeProfileDTO.getName());
        optionalHomeProfile.get().setAddress(homeProfileDTO.getAddress());
        optionalHomeProfile.get().setPricelist(homeProfileDTO.getPricelist());
        optionalHomeProfile.get().setNumberOfRooms(homeProfileDTO.getNumberOfRooms());
        optionalHomeProfile.get().setNumberOfBeds(homeProfileDTO.getNumberOfRooms());
        optionalHomeProfile.get().setPromoDescription(homeProfileDTO.getPromoDescription());
        optionalHomeProfile.get().setExtraService(homeProfileDTO.getExtraService());
        if(homeProfileDTO.getInteriorImage() != null && !homeProfileDTO.getInteriorImage().equals("")) {
            optionalHomeProfile.get().setInteriorImage(homeProfileDTO.getInteriorImage());
        }

        if(homeProfileDTO.getExteriorImage() != null && !homeProfileDTO.getExteriorImage().equals("")) {
            optionalHomeProfile.get().setExteriorImage(homeProfileDTO.getExteriorImage());
        }

        optionalHomeProfile.get().setBehaviourRules(homeProfileDTO.getBehaviourRules());
        if(homeProfileDTO.getownerId() != null) {
            optionalHomeProfile.get().setOwnerId(homeProfileDTO.getownerId());
        }

        return homeProfileRepository.save(optionalHomeProfile.get());
    }

    @Override
    public boolean delete(Long id) {
        Optional<HomeProfile> optionalHomeProfile = homeProfileRepository.findById(id);
        if(!canOwnerDelete(optionalHomeProfile.get().getId())){
            return false;
        }
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

        List<HomeProfile> homes =  homeProfileRepository.findAllByDeleted(false);
        List<HomeProfile> results = new ArrayList<>();

        for(HomeProfile home: homes){
            if(home.getName().toLowerCase().contains(dto.getSearchTerm().toLowerCase()) || home.getAddress().toLowerCase().contains(dto.getSearchTerm().toLowerCase()))
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

    @Override
    public boolean canOwnerDelete(Long houseId) {
        List<HomeReservation> reservations = reservationRepository.findAll();
        for(HomeReservation reservation: reservations){
            if(reservation.getHomeProfile().getId().equals(houseId)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canOwnerEdit(Long houseId) {
        List<HomeReservation> reservations = reservationRepository.findAll();
        for(HomeReservation reservation: reservations){
            if(reservation.getHomeProfile().getId().equals(houseId)){
                return false;
            }
        }
        return true;
    }



}
