package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.LoyaltyProgramme;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.repository.LoyaltyProgrammeRepository;
import com.ISA.service.definition.LoyaltyProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoyaltyProgrammeServiceImpl implements LoyaltyProgrammeService {

    @Autowired
    private LoyaltyProgrammeRepository loyaltyProgrammeRepository;

    @Override
    public LoyaltyProgramme add(LoyaltyProgrammeDTO loyaltyProgrammeDTO) {

        LoyaltyProgramme lp = new LoyaltyProgramme();

        lp.setLoyaltyId(loyaltyProgrammeDTO.getLoyaltyId());
        lp.setNumberOfAllReservations(loyaltyProgrammeDTO.getNumberOfAllReservations());
        lp.setNumberOfBoatReservations(loyaltyProgrammeDTO.getNumberOfBoatReservations());
        lp.setNumberOfHouseReservations(loyaltyProgrammeDTO.getNumberOfHouseReservations());
        lp.setNumberOfInstructorReservations(loyaltyProgrammeDTO.getNumberOfInstructorReservations());
        lp.setAction(loyaltyProgrammeDTO.getAction());
        lp.setName(loyaltyProgrammeDTO.getName());

        return loyaltyProgrammeRepository.save(lp);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public LoyaltyProgramme edit(Long id, LoyaltyProgrammeDTO loyaltyProgrammeDTO) {

        Optional<LoyaltyProgramme> optionalLoyaltyProgramme = loyaltyProgrammeRepository.findById(id);

        optionalLoyaltyProgramme.get().setNumberOfAllReservations(loyaltyProgrammeDTO.getNumberOfAllReservations());
        optionalLoyaltyProgramme.get().setNumberOfBoatReservations(loyaltyProgrammeDTO.getNumberOfBoatReservations());
        optionalLoyaltyProgramme.get().setNumberOfHouseReservations(loyaltyProgrammeDTO.getNumberOfHouseReservations());
        optionalLoyaltyProgramme.get().setNumberOfInstructorReservations(loyaltyProgrammeDTO.getNumberOfInstructorReservations());
        optionalLoyaltyProgramme.get().setAction(loyaltyProgrammeDTO.getAction());
        optionalLoyaltyProgramme.get().setName(loyaltyProgrammeDTO.getName());

        return loyaltyProgrammeRepository.save(optionalLoyaltyProgramme.get());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean delete(Long id) {
        Optional<LoyaltyProgramme> optionalLoyaltyProgramme = loyaltyProgrammeRepository.findById(id);

        optionalLoyaltyProgramme.get().setDeleted(true);
        loyaltyProgrammeRepository.save(optionalLoyaltyProgramme.get());
        return true;
    }
}
