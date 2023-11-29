package com.servicebook.service;

import com.servicebook.models.Proveedor;
import com.servicebook.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Proveedor findByEmail(String email){
        return proveedorRepository.findByEmail(email).orElse(null);
    }
    
    public List<Proveedor> findByAlta(){
        return proveedorRepository.listarProveedores();
    }

    public void save(Proveedor proveedor){
        proveedorRepository.save(proveedor);
    }
}
