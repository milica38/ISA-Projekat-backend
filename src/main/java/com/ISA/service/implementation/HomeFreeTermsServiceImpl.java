package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.service.definition.HomeFreeTermsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.EmailService;

import java.util.List;

@Service
public class HomeFreeTermsServiceImpl implements HomeFreeTermsService {

    @Autowired
    private HomeFreeTermsRepository homeFreeTermsRepository;

    @Autowired
    private HomeProfileRepository homeProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public List<HomeFreeTerms> getAll(Long houseId)
    {
        return homeFreeTermsRepository.findAllByHomeProfileId(houseId);
    }

    @Override
    public HomeFreeTerms add(HomeFreeTermsDTO homeFreeTermsDTO) {

        List<HomeFreeTerms> freeTerms = homeFreeTermsRepository.findAllByHomeProfileId(homeFreeTermsDTO.getHouseId());

        for(HomeFreeTerms freeTerm: freeTerms) {

            if(homeFreeTermsDTO.getStartDate().equals(freeTerm.getStartDate()) || homeFreeTermsDTO.getEndDate().equals(freeTerm.getEndDate()) || homeFreeTermsDTO.getStartDate().equals(freeTerm.getEndDate()) || homeFreeTermsDTO.getEndDate().equals(freeTerm.getStartDate()) && homeFreeTermsDTO.getHouseId().equals(freeTerm.getHomeProfile().getId())){
                return null;
            }

            if(homeFreeTermsDTO.getStartDate().after(freeTerm.getStartDate()) && homeFreeTermsDTO.getStartDate().before(freeTerm.getEndDate()) && homeFreeTermsDTO.getHouseId().equals(freeTerm.getHomeProfile().getId())){
                return null;
            }

            if(homeFreeTermsDTO.getEndDate().after(freeTerm.getStartDate()) && homeFreeTermsDTO.getEndDate().before(freeTerm.getEndDate()) && homeFreeTermsDTO.getHouseId().equals(freeTerm.getHomeProfile().getId())){
                return null;
            }
        }

        List<User> users = userRepository.findAllByType("Client");
        HomeProfile homeProfile = homeProfileRepository.findById(homeFreeTermsDTO.getHouseId()).get();

        for(User user: users) {
            emailService.sendEmailForHouseAction(user, homeProfile);
        }

        HomeFreeTerms homeFreeTerms = new HomeFreeTerms();
        homeFreeTerms.setStartDate(homeFreeTermsDTO.getStartDate());
        homeFreeTerms.setEndDate(homeFreeTermsDTO.getEndDate());
        homeFreeTerms.setHomeProfile(homeProfile);
        homeFreeTerms.setActionPrice(homeFreeTermsDTO.getActionPrice());
        homeFreeTerms.setAction(homeFreeTermsDTO.isAction());
        return homeFreeTermsRepository.save(homeFreeTerms);
    }

}
