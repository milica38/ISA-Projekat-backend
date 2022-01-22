package com.ISA.service.implementation;

import com.ISA.domain.dto.SearchFreeBoatsDTO;
import com.ISA.domain.model.BoatFreeTerms;
import com.ISA.domain.model.BoatProfile;
import com.ISA.repository.BoatFreeTermsRepository;
import com.ISA.service.definition.SearchFreeBoatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchFreeBoatsServiceImpl implements SearchFreeBoatsService {

    @Autowired
    BoatFreeTermsRepository freeTermsRepository;

    @Override
    public List<BoatProfile> findAllFree(SearchFreeBoatsDTO dto) {

        List<BoatProfile> boats = new ArrayList<>();
        List<BoatFreeTerms> freeTerms = freeTermsRepository.findAll();

        for (BoatFreeTerms term: freeTerms) {
            if(term.isAction() != true && (dto.getStartDate().after(term.getStartDate()) || isDateEqual(dto.getStartDate(), term.getStartDate())) &&  (dto.getEndDate().before(term.getEndDate()) || isDateEqual(dto.getEndDate(), term.getEndDate())) && term.getBoatProfile().getAddress().toLowerCase().contains(dto.getAddress().toLowerCase())){

                if(!boatExists(term.getBoatProfile(), boats)){
                    boats.add(term.getBoatProfile());
                }
            }
        }
        return boats;
    }

    @Override
    public Boolean boatExists(BoatProfile boat, List<BoatProfile> boats) {

        for(BoatProfile profile: boats){
            if(profile.getId().equals(boat.getId()))
                return true;
        }
        return false;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }
}
