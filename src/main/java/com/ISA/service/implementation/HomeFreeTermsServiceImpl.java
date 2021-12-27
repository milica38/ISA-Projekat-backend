package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.service.definition.HomeFreeTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeFreeTermsServiceImpl implements HomeFreeTermsService {

    @Autowired
    private HomeFreeTermsRepository homeFreeTermsRepository;

    @Autowired
    private HomeProfileRepository homeProfileRepository;

    public List<HomeFreeTerms> getAll(Long houseId)
    {
        return homeFreeTermsRepository.findAllByHomeProfileId(houseId);
    }

    @Override
    public HomeFreeTerms add(HomeFreeTermsDTO homeFreeTermsDTO) {

        HomeFreeTerms homeFreeTerms = new HomeFreeTerms();

        HomeProfile homeProfile = homeProfileRepository.findById(homeFreeTermsDTO.getHouseId()).get();

        homeFreeTerms.setStartDate(homeFreeTermsDTO.getStartDate());
        homeFreeTerms.setEndDate(homeFreeTermsDTO.getEndDate());
        homeFreeTerms.setHomeProfile(homeProfile);
        return homeFreeTermsRepository.save(homeFreeTerms);
    }
}
