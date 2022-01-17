package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.BoatReservationRepository;
import com.ISA.service.definition.BoatProfileService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoatProfileServiceImpl implements BoatProfileService {

    @Autowired
    private BoatProfileRepository boatProfileRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BoatReservationRepository reservationRepository;

    @Override
    public List<BoatProfile> getAll() { return boatProfileRepository.findAll(); }

    @Override
    public List<BoatProfile> getMyNotDeletedBoats() {
        User user = userService.getCurrentUser();

        return boatProfileRepository.findAllByOwnerIdAndDeleted(user.getId(), false);
    }

    @Override
    public List<BoatProfile> getAllNotDeleted() { return boatProfileRepository.findAllByDeleted(false); }

    @Override
    public BoatProfile get(Long id) { return boatProfileRepository.findById(id).get(); }

    @Override
    public BoatProfile add(BoatProfileDTO boatProfileDTO) {

        BoatProfile bp = new BoatProfile();

        bp.setOwnerId(userService.getCurrentUser().getId());
        bp.setNavEquipment(boatProfileDTO.getNavEquipment());
        bp.setExtraPrice(boatProfileDTO.getExtraPrice());
        bp.setAddress(boatProfileDTO.getAddress());
        bp.setExtraService(boatProfileDTO.getExtraService());
        bp.setBehaviourRules(boatProfileDTO.getBehaviourRules());
        bp.setExteriorImage(boatProfileDTO.getExteriorImage());
        bp.setInteriorImage(boatProfileDTO.getInteriorImage());
        bp.setCancelConditions(boatProfileDTO.getCancelConditions());
        bp.setCapacity(boatProfileDTO.getCapacity());
        bp.setPriceList(boatProfileDTO.getPricelist());
        bp.setLength(boatProfileDTO.getLength());
        bp.setEngineNumber(boatProfileDTO.getEngineNumber());
        bp.setEnginePower(boatProfileDTO.getEnginePower());
        bp.setFishingEquipment(boatProfileDTO.getFishingEquipment());
        bp.setPromoDescription(boatProfileDTO.getPromoDescription());
        bp.setMaxSpeed(boatProfileDTO.getMaxSpeed());
        bp.setName(boatProfileDTO.getName());
        bp.setType(boatProfileDTO.getType());
        bp.setLatitude(boatProfileDTO.getLatitude());
        bp.setLongitude(boatProfileDTO.getLongitude());
        return boatProfileRepository.save(bp);
    }

    public BoatProfile edit(Long id, BoatProfileDTO boatProfileDTO) {

        Optional<BoatProfile> optionalBoatProfile = boatProfileRepository.findById(id);
        if(!canOwnerDelete(id)){
            return null;
        }
        optionalBoatProfile.get().setNavEquipment(boatProfileDTO.getNavEquipment());
        optionalBoatProfile.get().setExtraPrice(boatProfileDTO.getExtraPrice());
        optionalBoatProfile.get().setAddress(boatProfileDTO.getAddress());
        optionalBoatProfile.get().setExtraService(boatProfileDTO.getExtraService());
        optionalBoatProfile.get().setBehaviourRules(boatProfileDTO.getBehaviourRules());
        optionalBoatProfile.get().setCancelConditions(boatProfileDTO.getCancelConditions());
        optionalBoatProfile.get().setCapacity(boatProfileDTO.getCapacity());
        optionalBoatProfile.get().setPriceList(boatProfileDTO.getPricelist());
        optionalBoatProfile.get().setLength(boatProfileDTO.getLength());
        optionalBoatProfile.get().setEngineNumber(boatProfileDTO.getEngineNumber());
        optionalBoatProfile.get().setEnginePower(boatProfileDTO.getEnginePower());
        optionalBoatProfile.get().setFishingEquipment(boatProfileDTO.getFishingEquipment());
        optionalBoatProfile.get().setPromoDescription(boatProfileDTO.getPromoDescription());
        optionalBoatProfile.get().setMaxSpeed(boatProfileDTO.getMaxSpeed());
        optionalBoatProfile.get().setType(boatProfileDTO.getType());
        optionalBoatProfile.get().setName(boatProfileDTO.getName());
        if(boatProfileDTO.getInteriorImage() != null && !boatProfileDTO.getInteriorImage().equals("")) {
            optionalBoatProfile.get().setInteriorImage(boatProfileDTO.getInteriorImage());
        }
        if(boatProfileDTO.getExteriorImage() != null && !boatProfileDTO.getExteriorImage().equals("")) {
            optionalBoatProfile.get().setExteriorImage(boatProfileDTO.getExteriorImage());
        }
        if(boatProfileDTO.getownerId() != null) {
            optionalBoatProfile.get().setOwnerId(boatProfileDTO.getownerId());
        }

        return boatProfileRepository.save(optionalBoatProfile.get());
    }

    @Override
    public boolean delete(Long id) {

        Optional<BoatProfile> optionalBoatProfile = boatProfileRepository.findById(id);
        if(!canOwnerDelete(optionalBoatProfile.get().getId())){
            return false;
        }
        optionalBoatProfile.get().setDeleted(true);
        boatProfileRepository.save(optionalBoatProfile.get());
        return true;
    }

    @Override
    public List<BoatProfile> filterBoats(BoatProfileDTO dto) {

        List<BoatProfile> results = new ArrayList<>();
        List<BoatProfile> boats = boatProfileRepository.findAllByDeleted(false);

        for(BoatProfile profile: boats){
            if(profile.getName().toLowerCase().contains(dto.getSearchTerm().toLowerCase()) || profile.getAddress().toLowerCase().contains(dto.getSearchTerm().toLowerCase())
            || profile.getFishingEquipment().toLowerCase().contains(dto.getSearchTerm().toLowerCase())
                    || profile.getBehaviourRules().toLowerCase().contains(dto.getSearchTerm().toLowerCase())){
                if(!boatsExists(profile, results))
                    results.add(profile);
            }
        }
        return results;
    }

    @Override
    public boolean boatsExists(BoatProfile boat, List<BoatProfile> boats) {

        for(BoatProfile profile: boats){
            if(profile.getId().equals(boat.getId()))
                return true;
        }
        return false;
    }

    @Override
    public boolean canOwnerEdit(Long boatId) {
        List<BoatReservation> reservations = reservationRepository.findAll();
        Date today = new Date();
        for(BoatReservation reservation: reservations){
            if(reservation.getBoatProfile().getId().equals(boatId) && ((reservation.getStartDate().before(today) || isDateEqual(reservation.getStartDate(), today) ) && (reservation.getEndDate().after(today) || isDateEqual(reservation.getEndDate(), today)) || reservation.getStartDate().after(today))){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canOwnerDelete(Long boatId) {
        List<BoatReservation> reservations = reservationRepository.findAll();
        Date today = new Date();
        for(BoatReservation reservation: reservations){
            if(reservation.getBoatProfile().getId().equals(boatId) && ((reservation.getStartDate().before(today) || isDateEqual(reservation.getStartDate(), today) ) && (reservation.getEndDate().after(today) || isDateEqual(reservation.getEndDate(), today)) || reservation.getStartDate().after(today))){
                return false;
            }
        }
        return true;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();
    }
}
