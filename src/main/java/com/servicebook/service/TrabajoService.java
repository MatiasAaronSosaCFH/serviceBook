package com.servicebook.service;

import com.servicebook.models.Calificacion;
import com.servicebook.models.Trabajo;
import com.servicebook.models.enums.Estrellas;
import com.servicebook.repository.TrabajoRepository;
import com.servicebook.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepository trabajoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;
    
    @Autowired
    private CalificacionService calificacionService;

//    @Autowired
//    private Trabajo trabajo;
    @Transactional
    public void crearTrabajo(Long idCliente, Long idProvedor) {
        Optional<Cliente> respuestaCliente = usuarioRepository.findById(idCliente).isPresent().get();
        Optional<Proveedor> respuestaProveedor = proveedorRepository.findById(idProveedor);// usar servicio de proveedor y cliente
        Trabajo trabajo = new Trabajo();
        trabajo.setCliente(respuestaCliente);
        trabajo.setProveedor(idProvedor);
    }

    @Transactional
    public void confirmacionProveedor(Long id) {
        Optional<Trabajo> resp = trabajoRepository.findById(id);
        Trabajo trabajo = resp.get();
        trabajo.setTerminoProveedor(Boolean.TRUE);
        trabajoRepository.save(trabajo);
    }

    @Transactional
    public void confirmacionCliente(Long id) {
        Optional<Trabajo> resp = trabajoRepository.findById(id);
        Trabajo trabajo = resp.get();
        trabajo.setTerminoCliente(Boolean.TRUE);
        trabajoRepository.save(trabajo);
    }

    @Transactional
    public void calificar(Long id,Estrellas estrellas, String descripcion){
        Optional<Trabajo> resp = trabajoRepository.findById(id);
        Trabajo trabajo = resp.get();
        trabajo.setCalificacion(calificacionService.calificar(estrellas, descripcion));//
    }
    
}
