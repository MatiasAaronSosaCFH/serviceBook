package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Calificacion;
import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import com.servicebook.models.Foto;
import com.servicebook.models.dtos.TrabajoDtoRecibido;
import com.servicebook.models.enums.Estrellas;
import com.servicebook.repository.CalificacionRepository;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.repository.TrabajoRepository;
import java.util.Date;
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
    private ClienteService  clienteService;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProveedorService proveedorService;
	 @Autowired
    private CalificacionRepository calificacionRepository;
    @Autowired
    private CalificacionService calificacionService;
    
    @Autowired
    private FotoService fotoService;
	 
    @Transactional
    public void crearTrabajo(Long idCliente, Long idProveedor, String tituloTrabajo, String descripcionTrabajo) {
		 System.out.println("idcli: " +idCliente +" idprov: " + idProveedor +" titulo: "+ tituloTrabajo + " desc: " + descripcionTrabajo);
		 Proveedor proveedor = new Proveedor();
		proveedor = proveedorService.getOne(idProveedor);
		 
 		 System.out.println("prov: " );		  

		 Cliente cliente = new Cliente();
		 cliente = clienteService.getOne(idCliente);


		 System.out.println("cliente: " );		  

		 Trabajo trabajo = new Trabajo();
        trabajo.setCliente(cliente);
        trabajo.setProveedor(proveedor);
//		 System.out.println("trabajo:" + trabajo.toString());
	trabajo.setTituloTrabajo(tituloTrabajo);
	trabajo.setDescripcionTrabajo(new Date() + ":\n "+ descripcionTrabajo);
	trabajo.setAlta(Boolean.TRUE);
	trabajo.setEstaAceptadoCliente(Boolean.FALSE);
	trabajo.setFechaTrabajo(new Date());
	trabajo.setHorasTrabajo(0);
	trabajo.setTerminoCliente(Boolean.FALSE);
	trabajo.setTerminoProveedor(Boolean.FALSE);
	trabajo.setCalificacion(null);

//			 System.out.println("trabajo1:" + trabajo.toString());
	trabajoRepository.save(trabajo);
    }
	 
    @Transactional
    public void agendarTrabajo(Long id, String tituloTrabajo, String descripcionTrabajo, Date fechaTrabajo, Integer horasTrabajo) {
		Trabajo trabajo = new Trabajo();
		 Optional<Trabajo> respuesta = trabajoRepository.buscarPorId(id);
		  if (respuesta.isPresent() && respuesta.get().getAlta() == true) {
			trabajo = respuesta.get();
			trabajo.setTituloTrabajo(tituloTrabajo);
			trabajo.setDescripcionTrabajo(trabajo.getDescripcionTrabajo() + ":\n " + new Date() + ":\n "+ descripcionTrabajo);
			trabajo.setFechaTrabajo(fechaTrabajo);
			trabajo.setHorasTrabajo(horasTrabajo);
			
			trabajoRepository.save(trabajo);
		 }        
    }	 

    @Transactional
    public void aceptarTrabajo(Long id, String descripcionTrabajo, Boolean estaAceptadoCliente) {
		Trabajo trabajo = new Trabajo();
		 Optional<Trabajo> respuesta = trabajoRepository.buscarPorId(id);
		  if (respuesta.isPresent() && respuesta.get().getAlta() == true) {
			trabajo = respuesta.get();
			trabajo.setDescripcionTrabajo(trabajo.getDescripcionTrabajo() + ":\n " + new Date() + ":\n "+ descripcionTrabajo);
			trabajo.setEstaAceptadoCliente(estaAceptadoCliente);
			
			trabajoRepository.save(trabajo);
		 }        
    }	
	 
    @Transactional
    public void rechazarTrabajo(Long id, String descripcionTrabajo) {
		Trabajo trabajo = new Trabajo();
		 Optional<Trabajo> respuesta = trabajoRepository.buscarPorId(id);
		  if (respuesta.isPresent() && respuesta.get().getAlta().equals(Boolean.TRUE)) {
			trabajo = respuesta.get();
			trabajo.setDescripcionTrabajo(trabajo.getDescripcionTrabajo() + ":\n " + new Date() + ":\n "+ descripcionTrabajo);
			trabajo.setAlta(Boolean.FALSE);
			
			trabajoRepository.save(trabajo);
		 }        
    }	 

    @Transactional
    public void terminarTrabajoProveedor(Long id, String descripcionTrabajo, Boolean terminoProveedor) {
		Trabajo trabajo = new Trabajo();
		 Optional<Trabajo> respuesta = trabajoRepository.buscarPorId(id);
		  if (respuesta.isPresent() && respuesta.get().getAlta() == true) {
			trabajo = respuesta.get();
			trabajo.setDescripcionTrabajo(trabajo.getDescripcionTrabajo() + ":\n " + new Date() + ":\n "+ descripcionTrabajo);
			trabajo.setTerminoProveedor(terminoProveedor);
			
			trabajoRepository.save(trabajo);
		 }        
    }	 

@Transactional
public void terminarTrabajoCliente(Long id, Boolean terminoCliente, Estrellas estrellas, String descripcion) {
	Trabajo trabajo = new Trabajo();
	Optional<Trabajo> respuesta = trabajoRepository.buscarPorId(id);
	if (respuesta.isPresent() && respuesta.get().getAlta() == true) {
		trabajo = respuesta.get();
		trabajo.setTerminoCliente(terminoCliente);
		
		Calificacion calificacion = new Calificacion();
		calificacion.setTrabajo(trabajo);
		calificacion.setDescripcion(new Date() + ":\n "+ descripcion);
		calificacion.setEstrellas(estrellas);
		calificacion.setAlta(Boolean.TRUE);
		calificacionRepository.save(calificacion);
		
		Calificacion calificacionTrabajo = calificacionRepository.buscarPorIdTrabajo(id).orElse(null);
		
		trabajo.setCalificacion(calificacionTrabajo);
		
		trabajoRepository.save(trabajo);
	}	
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
    public void calificar(Long id, Estrellas estrellas, String descripcion) throws MiException{
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

    public Trabajo convertirDtoRecibido(TrabajoDtoRecibido trabajo){
        Trabajo trabajoFinal = new Trabajo();
        trabajoFinal.setCalificacion(calificacionService.convertirDtoRecibido(trabajo.calificacion()));
        trabajoFinal.setAlta(true);
        trabajoFinal.setCliente(clienteRepository.buscarPorId(trabajo.cliente()).orElse(null));
        trabajoFinal.setProveedor(proveedorRepository.buscarPorId(trabajo.proveedor()).orElse(null));
        trabajoFinal.setTerminoCliente(trabajo.terminoCliente());
        trabajoFinal.setTerminoProveedor(trabajo.terminoProveedor());
        return trabajoFinal;
    }
}
