package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatFreeTermsDTO;
import com.ISA.domain.model.BoatFreeTerms;
import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.BoatFreeTermsRepository;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.BoatFreeTermsService;
import com.ISA.service.definition.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatFreeTermsServiceImpl implements BoatFreeTermsService {

    @Autowired
    private BoatFreeTermsRepository boatFreeTermsRepository;

    @Autowired
    private BoatProfileRepository boatProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public List<BoatFreeTerms> getAll(Long boatId) {

        return  boatFreeTermsRepository.findAllByBoatProfileId(boatId);
    }

    @Override
    public BoatFreeTerms add(BoatFreeTermsDTO boatFreeTermsDTO) {

        List<BoatFreeTerms> freeTerms = boatFreeTermsRepository.findAllByBoatProfileId(boatFreeTermsDTO.getBoatId());
        for(BoatFreeTerms freeTerm: freeTerms) {
            if(boatFreeTermsDTO.getStartDate().equals(freeTerm.getStartDate()) || boatFreeTermsDTO.getEndDate().equals(freeTerm.getEndDate()) || boatFreeTermsDTO.getStartDate().equals(freeTerm.getEndDate()) || boatFreeTermsDTO.getEndDate().equals(freeTerm.getStartDate()) && boatFreeTermsDTO.getBoatId().equals(freeTerm.getBoatProfile().getId())){
                return null;
            }

            if(boatFreeTermsDTO.getStartDate().after(freeTerm.getStartDate()) && boatFreeTermsDTO.getStartDate().before(freeTerm.getEndDate()) && boatFreeTermsDTO.getBoatId().equals(freeTerm.getBoatProfile().getId())){
                return null;
            }

            if(boatFreeTermsDTO.getEndDate().after(freeTerm.getStartDate()) && boatFreeTermsDTO.getEndDate().before(freeTerm.getEndDate()) && boatFreeTermsDTO.getBoatId().equals(freeTerm.getBoatProfile().getId())){
                return null;
            }
        }

        List<User> users = userRepository.findAllByType("Client");
        BoatProfile boatProfile = boatProfileRepository.findById(boatFreeTermsDTO.getBoatId()).get();

        for(User user: users) {
            emailService.sendEmailForBoatAction(user, boatProfile);
        }

        BoatFreeTerms boatFreeTerms = new BoatFreeTerms();

        boatFreeTerms.setStartDate(boatFreeTermsDTO.getStartDate());
        boatFreeTerms.setEndDate(boatFreeTermsDTO.getEndDate());
        boatFreeTerms.setBoatProfile(boatProfile);
        boatFreeTerms.setActionPrice(boatFreeTermsDTO.getActionPrice());
        boatFreeTerms.setAction(boatFreeTermsDTO.isAction());

        return boatFreeTermsRepository.save(boatFreeTerms);
    }
}
