package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureFreeTermsDTO;
import com.ISA.domain.dto.BoatFreeTermsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.*;
import com.ISA.service.definition.AdventureFreeTermsService;
import com.ISA.service.definition.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdventureFreeTermsServiceImpl implements AdventureFreeTermsService {

    @Autowired
    private AdventureFreeTermsRepository freeTermsRepository;

    @Autowired
    private AdventureSubscriptionsRepository adventureSubscriptionsRepository;

    @Autowired
    private AdventureProfileRepository adventureProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public List<AdventureFreeTerms> getAll(Long adventureId) {
        return freeTermsRepository.findAllByAdventureProfileId(adventureId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AdventureFreeTerms add(AdventureFreeTermsDTO adventureFreeTermsDTO) {

        List<AdventureFreeTerms> freeTerms = freeTermsRepository.findAllByAdventureProfileId(adventureFreeTermsDTO.getAdventureId());
        for(AdventureFreeTerms freeTerm: freeTerms) {
            if(adventureFreeTermsDTO.getStartDate().equals(freeTerm.getStartDate()) || adventureFreeTermsDTO.getEndDate().equals(freeTerm.getEndDate()) || adventureFreeTermsDTO.getStartDate().equals(freeTerm.getEndDate()) || adventureFreeTermsDTO.getEndDate().equals(freeTerm.getStartDate()) && adventureFreeTermsDTO.getAdventureId().equals(freeTerm.getAdventureProfile().getId())){
                return null;
            }

            if(adventureFreeTermsDTO.getStartDate().after(freeTerm.getStartDate()) && adventureFreeTermsDTO.getStartDate().before(freeTerm.getEndDate()) && adventureFreeTermsDTO.getAdventureId().equals(freeTerm.getAdventureProfile().getId())){
                return null;
            }

            if(adventureFreeTermsDTO.getEndDate().after(freeTerm.getStartDate()) && adventureFreeTermsDTO.getEndDate().before(freeTerm.getEndDate()) && adventureFreeTermsDTO.getAdventureId().equals(freeTerm.getAdventureProfile().getId())){
                return null;
            }
        }


        List<AdventureSubscriptions> subscribedUsers = adventureSubscriptionsRepository.findAllByClientTypeAndIsSubscribed("Client", true);
        AdventureProfile adventureProfile = adventureProfileRepository.findById(adventureFreeTermsDTO.getAdventureId()).get();

        for(AdventureSubscriptions subscription: subscribedUsers) {
            emailService.sendEmailForAdventureAction(subscription.getClient(), adventureProfile);
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
