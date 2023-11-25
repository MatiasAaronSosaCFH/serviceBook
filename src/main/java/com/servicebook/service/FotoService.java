package com.servicebook.service;

import com.servicebook.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;
}
