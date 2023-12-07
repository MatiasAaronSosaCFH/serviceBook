/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.service;

import com.servicebook.models.FotoProveedor;
import com.servicebook.repository.FotoProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SantiagoPaguaga
 */
@Service
public class FotoProveedorService {
  
    @Autowired
    private FotoProveedorRepository fotoProveedorRepository;

    @Transactional
    public FotoProveedor guardar(FotoProveedor fotoProveedor) {
        return fotoProveedorRepository.save(fotoProveedor);
    }
  
}
