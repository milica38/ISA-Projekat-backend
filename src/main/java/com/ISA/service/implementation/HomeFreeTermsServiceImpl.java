package com.ISA.service.implementation;

import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.service.definition.HomeFreeTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeFreeTermsServiceImpl implements HomeFreeTermsService {

    @Autowired
    private HomeFreeTermsRepository homeFreeTermsRepository;

    @Override
    public List<HomeFreeTerms> getAll() { return homeFreeTermsRepository.findAll(); }
}
