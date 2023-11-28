package com.servicebook.service;

import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import com.servicebook.models.enums.Estrellas;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private CalificacionService calificacionService;

    @Transactional
    public void crearTrabajo(Long idCliente, Long idProveedor) {
        Optional<Cliente> respuestaCliente = clienteRepository.buscarPorId(idCliente);
        Optional<Proveedor> respuestaProveedor = proveedorRepository.buscarPorId(idProveedor);
        Trabajo trabajo = new Trabajo();
        trabajo.setCliente(respuestaCliente.orElse(new Cliente()));
        trabajo.setProveedor(respuestaProveedor.orElse(new Proveedor()));
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
    public void calificar(Long id, Estrellas estrellas, String descripcion) {
        Optional<Trabajo> resp = trabajoRepository.findById(id);
        Trabajo trabajo = resp.get();
        trabajo.setCalificacion(calificacionService.calificar(estrellas, descripcion));//
    }

}
