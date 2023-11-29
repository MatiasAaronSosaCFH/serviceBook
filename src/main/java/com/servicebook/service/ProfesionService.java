package com.servicebook.service;

import com.servicebook.repository.ProfesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionService {

    @Autowired
    private ProfesionRepository profesionRepository;

}