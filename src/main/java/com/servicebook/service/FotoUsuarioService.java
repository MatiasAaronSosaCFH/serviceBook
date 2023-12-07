package com.servicebook.service;

import com.servicebook.models.FotoUsuario;
import com.servicebook.repository.FotoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotoUsuarioService {

    @Autowired
    private FotoUsuarioRepository fotoUsuarioRepository;

    @Transactional
    public FotoUsuario guardar(FotoUsuario fotoUsuario) {
        return fotoUsuarioRepository.save(fotoUsuario);
    }

}
