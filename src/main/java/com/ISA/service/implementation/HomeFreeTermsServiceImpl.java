package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.service.definition.HomeFreeTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ISA.repository.UserRepository;
import com.ISA.service.definition.EmailService;

import java.util.ArrayList;
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
    private EmailService emailService;

    public List<HomeFreeTerms> getAll(Long houseId)
    {
        return homeFreeTermsRepository.findAllByHomeProfileId(houseId);
    }

    @Override
    public HomeFreeTerms add(HomeFreeTermsDTO homeFreeTermsDTO) {

        List<HomeFreeTerms> freeTerms = homeFreeTermsRepository.findAllByHomeProfileId(homeFreeTermsDTO.getHouseId());

        for(HomeFreeTerms freeTerm: freeTerms) {

            if(homeFreeTermsDTO.getStartDate().equals(freeTerm.getStartDate()) && homeFreeTermsDTO.getEndDate().equals(freeTerm.getEndDate())){
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

    @Override
    public List<HomeProfile> getAllActions() {
        List<HomeProfile> homes = new ArrayList<>();
        List<HomeFreeTerms> actions = homeFreeTermsRepository.findAllByIsAction(true);

        for (HomeFreeTerms term: actions) {
            if(term.isAction() == true ){
                if(!homeExists(term.getHomeProfile(), homes)){
                    homes.add(term.getHomeProfile());
                }
            }
        }
        return homes;
    }

    public Boolean homeExists(HomeProfile home, List<HomeProfile> homes) {

        for(HomeProfile profile: homes){
            if(profile.getId().equals(home.getId())){
                return true;
            }
        }
        return false;
    }

    public List<HomeFreeTerms> getAllActionDates(){
        return homeFreeTermsRepository.findAllByIsAction(true);
    }
}
