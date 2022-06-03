package com.ISA.service.implementation;

import com.ISA.domain.dto.LoyalProgrammeDTO;
import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.LoyalProgramme;
import com.ISA.domain.model.LoyaltyProgramme;
import com.ISA.repository.LoyalProgrammeRepository;
import com.ISA.repository.LoyaltyProgrammeRepository;
import com.ISA.service.definition.LoyalProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoyalProgrammeServiceImpl implements LoyalProgrammeService {
    @Autowired
    private LoyalProgrammeRepository loyalProgrammeRepository;

    @Override
    public LoyalProgramme add(LoyalProgrammeDTO loyalProgrammeDTO) {

        LoyalProgramme lp = new LoyalProgramme();

        lp.setId(loyalProgrammeDTO.getId());
        lp.setSilverPoints(loyalProgrammeDTO.getSilverPoints());
        lp.setGoldPoints(loyalProgrammeDTO.getGoldPoints());
        lp.setSilverAction(loyalProgrammeDTO.getSilverAction());
        lp.setGoldAction(loyalProgrammeDTO.getGoldAction());
        lp.setReservationPoints(loyalProgrammeDTO.getReservationPoints());
        lp.setReservedPoints(loyalProgrammeDTO.getReservedPoints());

        return loyalProgrammeRepository.save(lp);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public LoyalProgramme edit(int id, LoyalProgrammeDTO loyalProgrammeDTO) {

        Optional<LoyalProgramme> optionalLoyalProgramme = loyalProgrammeRepository.findById(id);

        optionalLoyalProgramme.get().setSilverPoints(loyalProgrammeDTO.getSilverPoints());
        optionalLoyalProgramme.get().setGoldPoints(loyalProgrammeDTO.getGoldPoints());
        optionalLoyalProgramme.get().setSilverAction(loyalProgrammeDTO.getSilverAction());
        optionalLoyalProgramme.get().setGoldAction(loyalProgrammeDTO.getGoldAction());
        optionalLoyalProgramme.get().setReservationPoints(loyalProgrammeDTO.getReservationPoints());
        optionalLoyalProgramme.get().setReservedPoints(loyalProgrammeDTO.getReservedPoints());

        return loyalProgrammeRepository.save(optionalLoyalProgramme.get());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean delete(int id) {
        Optional<LoyalProgramme> optionalLoyalProgramme = loyalProgrammeRepository.findById(id);

        optionalLoyalProgramme.get().setDeleted(true);
        loyalProgrammeRepository.save(optionalLoyalProgramme.get());
        return true;
    }

    public LoyalProgramme get(int id) {
        return loyalProgrammeRepository.findById(id).get();
    }
}
