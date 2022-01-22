package com.ISA.service.implementation;

import com.ISA.domain.dto.SearchFreeAdventuresDTO;
import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.repository.AdventureFreeTermsRepository;
import com.ISA.service.definition.SearchFreeAdventuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchFreeAdventuresServiceImpl implements SearchFreeAdventuresService {

    @Autowired
    AdventureFreeTermsRepository freeTermsRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<AdventureProfile> findAllFree(SearchFreeAdventuresDTO dto) {

        List<AdventureProfile> adventures = new ArrayList<>();
        List<AdventureFreeTerms> freeTerms = freeTermsRepository.findAll();


        for (AdventureFreeTerms term: freeTerms) {
            if(term.isAction() != true && (dto.getStartDate().after(term.getStartDate()) || isDateEqual(dto.getStartDate(), term.getStartDate())) &&  (dto.getEndDate().before(term.getEndDate()) || isDateEqual(dto.getEndDate(), term.getEndDate())) && term.getAdventureProfile().getAddress().toLowerCase().contains(dto.getAddress().toLowerCase()) && term.getAdventureProfile().getMaxNumberOfPeople() >= dto.getMaxNumberOfPeople()){

                if(!adventureExists(term.getAdventureProfile(), adventures)){
                    adventures.add(term.getAdventureProfile());
                }
            }
        }
        return adventures;
    }

    @Override
    public Boolean adventureExists(AdventureProfile adventure, List<AdventureProfile> adventures) {
        for(AdventureProfile profile: adventures){
            if(profile.getId().equals(adventure.getId())){
                return true;
            }
        }
        return false;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }
}
