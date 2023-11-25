package com.servicebook.service;

import com.servicebook.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;
}
