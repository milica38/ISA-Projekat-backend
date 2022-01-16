package com.ISA.service.implementation;

import com.ISA.domain.dto.SearchFreeHomesDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.service.definition.SearchFreeHomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchFreeHomesServiceImpl implements SearchFreeHomesService {

    @Autowired
    HomeFreeTermsRepository homeFreeTermsRepository;

    @Override
    public List<HomeProfile> findAllFree(SearchFreeHomesDTO dto) {

        List<HomeProfile> homes = new ArrayList<>();
        List<HomeFreeTerms> freeTerms = homeFreeTermsRepository.findAll();

        for (HomeFreeTerms term: freeTerms) {
            if(term.isAction() != true && (dto.getStartDate().after(term.getStartDate()) || isDateEqual(dto.getStartDate(), term.getStartDate())) &&  (dto.getEndDate().before(term.getEndDate()) || isDateEqual(dto.getEndDate(), term.getEndDate())) && term.getHomeProfile().getAddress().toLowerCase().contains(dto.getAddress().toLowerCase())){

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

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }


}
