package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureFreeTermsDTO;
import com.ISA.domain.dto.BoatFreeTermsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.*;
import com.ISA.service.definition.AdventureFreeTermsService;
import com.ISA.service.definition.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureFreeTermsServiceImpl implements AdventureFreeTermsService {

    @Autowired
    private AdventureFreeTermsRepository freeTermsRepository;



    @Autowired
    private AdventureProfileRepository adventureProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public List<AdventureFreeTerms> getAll(Long adventureId) {
        return freeTermsRepository.findAllByAdventureProfileId(adventureId);
    }


    public AdventureFreeTerms add(AdventureFreeTermsDTO adventureFreeTermsDTO) {

        List<AdventureFreeTerms> freeTerms = freeTermsRepository.findAllByAdventureProfileId(adventureFreeTermsDTO.getAdventureId());
        for(AdventureFreeTerms freeTerm: freeTerms) {
            if(adventureFreeTermsDTO.getStartDate().equals(freeTerm.getStartDate()) && adventureFreeTermsDTO.getEndDate().equals(freeTerm.getEndDate())){
                return null;
            }
        }

        List<User> users = userRepository.findAllByType("Client");
        AdventureProfile adventureProfile = adventureProfileRepository.findById(adventureFreeTermsDTO.getAdventureId()).get();

        for(User user: users) {
            emailService.sendEmailForAdventureAction(user, adventureProfile);
        }

        AdventureFreeTerms adventureFreeTerms = new AdventureFreeTerms();

        adventureFreeTerms.setStartDate(adventureFreeTermsDTO.getStartDate());
        adventureFreeTerms.setEndDate(adventureFreeTermsDTO.getEndDate());
        adventureFreeTerms.setAdventureProfile(adventureProfile);
        adventureFreeTerms.setActionPrice(adventureFreeTermsDTO.getActionPrice());
        adventureFreeTerms.setAction(adventureFreeTermsDTO.isAction());

        return freeTermsRepository.save(adventureFreeTerms);
    }
}
