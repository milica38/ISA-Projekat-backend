package com.ISA.service.implementation;

import com.ISA.domain.dto.SearchFreeHomesDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.service.definition.SearchFreeHomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Service
public class SearchFreeHomesServiceImpl implements SearchFreeHomesService {

    @Autowired
    HomeFreeTermsRepository homeFreeTermsRepository;

    @Override
    public List<HomeProfile> findAllFree(SearchFreeHomesDTO dto) {

        List<HomeProfile> homes = new ArrayList<>();
        List<HomeFreeTerms> freeTerms = homeFreeTermsRepository.findAll();


        for (HomeFreeTerms term: freeTerms) {
            if(term.isAction() != true && dto.getStartDate().after(term.getStartDate()) &&  dto.getEndDate().before(term.getEndDate()) && term.getHomeProfile().getAddress().toLowerCase().contains(dto.getAddress().toLowerCase())){

                if(!homeExists(term.getHomeProfile(), homes)){
                    homes.add(term.getHomeProfile());
                }
            }
        }
        return homes;
    }

    @Override
    public Boolean homeExists(HomeProfile home, List<HomeProfile> homes) {

        for(HomeProfile profile: homes){
            if(profile.getId().equals(home.getId())){
                return true;
            }
        }
        return false;
    }


}
