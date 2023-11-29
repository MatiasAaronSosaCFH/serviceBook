package com.servicebook.service;

import com.servicebook.models.Cliente;
import com.servicebook.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id){
        return clienteRepository.findById(id).orElse(null);
    }
    
    public Cliente findByEmail(String email){
        return clienteRepository.findByEmail(email).orElse(null);
    }
}
