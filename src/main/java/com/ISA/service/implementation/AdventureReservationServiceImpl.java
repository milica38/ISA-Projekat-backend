package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.AdventureReservation;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.repository.AdventureReservationRepository;
import com.ISA.service.definition.AdventureReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdventureReservationServiceImpl implements AdventureReservationService {
    @Autowired
    private AdventureReservationRepository adventureReservationRepository;


    @Override
    public List<AdventureReservation> getAll() {
        return adventureReservationRepository.findAll();
    }

    @Override
    public AdventureReservation get(Long id) {
        return adventureReservationRepository.findById(id).get();
    }

    @Override
    public AdventureReservation add(AdventureReservationDTO adventureReservationDTO) {

        AdventureReservation ar = new AdventureReservation();
        ar.setId(adventureReservationDTO.getId());
        ar.setReservationStart(adventureReservationDTO.getReservationStart());
        ar.setReservationPlace(adventureReservationDTO.getReservationPlace());
        ar.setDurationReservation(adventureReservationDTO.getDurationReservation());
        ar.setMaximumNumberOfPeople(adventureReservationDTO.getMaximumNumberOfPeople());
        ar.setExtraServices(adventureReservationDTO.getExtraServices());
        ar.setPrice(adventureReservationDTO.getPrice());

        return adventureReservationRepository.save(ar);
    }

    @Override
    public AdventureReservation edit(AdventureReservationDTO adventureReservationDTO) {

        Optional<AdventureReservation> optionalAdventureReservation = adventureReservationRepository.findById(adventureReservationDTO.getId());

        optionalAdventureReservation.get().setId(adventureReservationDTO.getId());
        optionalAdventureReservation.get().setReservationStart(adventureReservationDTO.getReservationStart());
        optionalAdventureReservation.get().setReservationPlace(adventureReservationDTO.getReservationPlace());
        optionalAdventureReservation.get().setDurationReservation(adventureReservationDTO.getDurationReservation());
        optionalAdventureReservation.get().setMaximumNumberOfPeople(adventureReservationDTO.getMaximumNumberOfPeople());
        optionalAdventureReservation.get().setExtraServices(adventureReservationDTO.getExtraServices());
        optionalAdventureReservation.get().setPrice(adventureReservationDTO.getPrice());


        return adventureReservationRepository.save(optionalAdventureReservation.get());
    }

    @Override
    public boolean delete(Long id) {
        Optional<AdventureReservation> optionalAdventureReservation = adventureReservationRepository.findById(id);
        optionalAdventureReservation.get().setDeleted(true);
        adventureReservationRepository.save(optionalAdventureReservation.get());
        return true;
    }
}
