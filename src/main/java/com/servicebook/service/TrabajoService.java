package com.servicebook.service;

import com.servicebook.models.Cliente;
import com.servicebook.models.Foto;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import com.servicebook.models.enums.Estrellas;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.repository.TrabajoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Autowired
    private FotoService fotoService;

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
        trabajoRepository.confirmacionProveedor(id);
    }

    @Transactional
    public void confirmacionCliente(Long id) {
        trabajoRepository.confirmacionCliente(id);
    }

    @Transactional
    public void calificar(Long id, Estrellas estrellas, String descripcion) {
        Optional<Trabajo> resp = trabajoRepository.findById(id);
        Trabajo trabajo = resp.get();
        trabajo.setCalificacion(calificacionService.calificar(estrellas, descripcion));
        trabajoRepository.save(trabajo);
    }

//    @Transactional
//    public List<String> subirFotos(Long id,MultipartFile archivo, String fotoId){
//        Optional<Trabajo> resp = trabajoRepository.findById(id);
//        Trabajo trabajo = resp.get();
//        List<Foto> fotos = fotoService.subirFoto(archivo, fotoId);
//        trabajo.setFotos(fotos);
//    }
    
}
