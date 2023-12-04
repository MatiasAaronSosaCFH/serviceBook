package com.servicebook.service;

import com.servicebook.repository.FotoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoUsuarioService {

    @Autowired
    private FotoUsuarioRepository fotoUsuarioRepository;


}
