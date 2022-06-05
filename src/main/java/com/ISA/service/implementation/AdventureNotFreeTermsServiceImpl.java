package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureFreeTermsDTO;
import com.ISA.domain.dto.AdventureNotFreeTermsDTO;
import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.domain.model.AdventureNotFreeTerms;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.AdventureFreeTermsRepository;
import com.ISA.repository.AdventureNotFreeTermsRepository;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.AdventureFreeTermsService;
import com.ISA.service.definition.AdventureNotFreeTermsService;
import com.ISA.service.definition.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdventureNotFreeTermsServiceImpl implements AdventureNotFreeTermsService {

    @Autowired
    private AdventureNotFreeTermsRepository notFreeTermsRepository;



    @Autowired
    private AdventureProfileRepository adventureProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public List<AdventureNotFreeTerms> getAll(Long adventureId) {
        return notFreeTermsRepository.findAllByAdventureProfileId(adventureId);
    }

    public AdventureNotFreeTerms add(AdventureNotFreeTermsDTO adventureNotFreeTermsDTO) {

        List<AdventureNotFreeTerms> notFreeTerms = notFreeTermsRepository.findAllByAdventureProfileId(adventureNotFreeTermsDTO.getAdventureId());
        for(AdventureNotFreeTerms notFreeTerm: notFreeTerms) {
            if(adventureNotFreeTermsDTO.getStartDate().equals(notFreeTerm.getStartDate()) || adventureNotFreeTermsDTO.getEndDate().equals(notFreeTerm.getEndDate()) || adventureNotFreeTermsDTO.getStartDate().equals(notFreeTerm.getEndDate()) || adventureNotFreeTermsDTO.getEndDate().equals(notFreeTerm.getStartDate()) && adventureNotFreeTermsDTO.getAdventureId().equals(notFreeTerm.getAdventureProfile().getId())){
                return null;
            }

            if(adventureNotFreeTermsDTO.getStartDate().after(notFreeTerm.getStartDate()) && adventureNotFreeTermsDTO.getStartDate().before(notFreeTerm.getEndDate()) && adventureNotFreeTermsDTO.getAdventureId().equals(notFreeTerm.getAdventureProfile().getId())){
                return null;
            }

            if(adventureNotFreeTermsDTO.getEndDate().after(notFreeTerm.getStartDate()) && adventureNotFreeTermsDTO.getEndDate().before(notFreeTerm.getEndDate()) && adventureNotFreeTermsDTO.getAdventureId().equals(notFreeTerm.getAdventureProfile().getId())){
                return null;
            }
        }

        List<User> users = userRepository.findAllByType("Client");
        AdventureProfile adventureProfile = adventureProfileRepository.findById(adventureNotFreeTermsDTO.getAdventureId()).get();



        AdventureNotFreeTerms adventureNotFreeTerms = new AdventureNotFreeTerms();

        adventureNotFreeTerms.setStartDate(adventureNotFreeTermsDTO.getStartDate());
        adventureNotFreeTerms.setEndDate(adventureNotFreeTermsDTO.getEndDate());
        adventureNotFreeTerms.setAdventureProfile(adventureProfile);


        return notFreeTermsRepository.save(adventureNotFreeTerms);
    }
}
