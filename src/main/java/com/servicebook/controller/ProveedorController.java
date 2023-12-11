package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.FotoProveedor;
import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Usuario;
import com.servicebook.models.dtos.ClienteDtoEnviado;
import com.servicebook.models.dtos.ProveedorDtoEnviado;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.ProfesionRepository;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.service.ClienteService;
import com.servicebook.service.ProveedorService;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import com.servicebook.service.CloudinaryService;
import com.servicebook.service.FotoProveedorService;
import com.servicebook.service.ProfesionService;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.hibernate.bytecode.BytecodeLogging.LOGGER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

  @Autowired
  private ProveedorService proveedorService;
  @Autowired
  private CloudinaryService cloudinaryService;
  @Autowired
  private FotoProveedorService fotoProveedorService;
  @Autowired
  private ProveedorRepository proveedorRepository;
  @Autowired
  private ProfesionRepository profesionRepository;
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ProfesionService profesionService;

  @GetMapping
  public String inicioIndex(ModelMap map) {

    map.addAttribute("proveedores", proveedorService.findByAlta());

    return "index.html";
  }

  @PostMapping
  public String guardarProveedor(@RequestParam String nombre,
          @RequestParam String email,
          @RequestParam String emailDeContacto,
          @RequestParam String numeroDeContacto,
          @RequestParam String password,
          ModelMap map) {

    Proveedor proveedor = new Proveedor();
    proveedor.setNombre(nombre);
    proveedor.setEmail(email);
    proveedor.setEmailDeContacto(emailDeContacto);
    proveedor.setNumeroDeContacto(numeroDeContacto);
    proveedor.setPassword(password);

    proveedorService.save(proveedor);

    return "index.html";
  }

  @PreAuthorize("hasAnyRole('ROLE_USER')")
  @GetMapping("/registrar/{id}")
  public String registrarProveedor(HttpSession session, ModelMap model, RedirectAttributes redirectAttributes) {
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");

    if (usuario != null && !proveedorService.verificarSiExisteEmail(usuario.getEmail())) {

      Long clienteId = usuario.getId();
      ClienteDtoEnviado clienteDto = clienteService.obtenerClienteConDirecciones(clienteId);
      if (clienteDto != null) {
        model.addAttribute("usuario", clienteDto);
        model.addAttribute("roles", Role.values());
        List<Profesion> profesiones = profesionRepository.listarProfesiones();
        model.addAttribute("profesiones", profesiones);
      }

      return "registrar_proveedor.html";

    } else {
      Long clienteId = usuario.getId();
      ClienteDtoEnviado clienteDto = clienteService.obtenerClienteConDirecciones(clienteId);
      if (clienteDto != null) {
        redirectAttributes.addFlashAttribute("usuario", clienteDto);
        redirectAttributes.addFlashAttribute("error", "Ya envió una solicitud para ser proveedor");
      }
      return "redirect:/inicio";
    }

  }

  @PreAuthorize("hasAnyRole('ROLE_USER')")
  @PostMapping("/registrar/{id}")
  public String registrarProveedor(@PathVariable Long id,
          @RequestParam String emailDeContacto,
          @RequestParam String numeroDeContacto,
          @RequestParam String presentacion,
          @RequestParam Integer precioPorHora,
          @RequestParam(required = false) Boolean disponible,
          @RequestParam("imagenes") MultipartFile[] imagenes,
          ModelMap map,
          RedirectAttributes redirectAttributes,
          HttpServletRequest request) {

    map.addAttribute("roles", Role.values());

    try {
      LOGGER.info("Comienzo del método registrarProveedor");
      
      String[] profesionesArray = request.getParameterValues("profesionesSeleccionadas");
      
      
      List<String> profesionesSeleccionadas = Arrays.asList(profesionesArray);

      List<Profesion> profesiones = new ArrayList<>();

      for (String profesionSeleccionada : profesionesSeleccionadas) {
        Profesion profesion = profesionService.obtenerProfesionPorNombre(profesionSeleccionada);
        profesiones.add(profesion);
      }

      // Crear el proveedor y obtener la instancia
      Proveedor proveedor = proveedorService.crearProveedor(id, emailDeContacto, numeroDeContacto, profesiones, presentacion, precioPorHora, disponible);

      // Procesar y guardar las imágenes
      for (MultipartFile imagen : imagenes) {
        BufferedImage entry = ImageIO.read(imagen.getInputStream());

        if (entry == null) {
          redirectAttributes.addFlashAttribute("error", "La imagen es nula");
          return "redirect:/proveedor/registrar/id";
        }

        Map resultado = cloudinaryService.subirFoto(imagen);
        FotoProveedor foto = new FotoProveedor();
        foto.setNombre((String) resultado.get("original_filename"));
        foto.setUrl((String) resultado.get("url"));
        foto.setFotoId((String) resultado.get("public_id"));
        foto.setProveedor(proveedor);

        // Guardar la foto y asociarla al proveedor
        foto = fotoProveedorService.guardar(foto);
      }

      // Guardar el proveedor con las imágenes asociadas
      proveedorRepository.save(proveedor);

      redirectAttributes.addFlashAttribute("exito", "Solicitud enviada correctamente!");

      // Redireccionar a la página de éxito o a donde sea necesario
      LOGGER.info("Fin del método registrarProveedor");
      return "redirect:/inicio";
    } catch (Exception ex) {
      // Manejar errores y redirigir con mensajes de error
      redirectAttributes.addFlashAttribute("error", ex.getMessage());
      return "redirect:/proveedor/registrar/id";
    }
  }

  @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR')")
  @PostMapping("/modificar/{id}")
  public String modificar(@PathVariable Long id, @ModelAttribute Proveedor proveedor, RedirectAttributes redirectAttributes, HttpSession session) {

    Usuario usuario = (Usuario) session.getAttribute("usuariosession");

    if (usuario != null) {
      Long proveedorId = usuario.getId();
      ProveedorDtoEnviado proveedorDto = proveedorService.obtenerProveedorConDirecciones(proveedorId);
      if (proveedorDto != null) {
        redirectAttributes.addFlashAttribute("usuario", proveedorDto);
      }
    }

    try {
      proveedorService.modificar(id, proveedor);
      redirectAttributes.addFlashAttribute("exito", "Proveedor modificado correctamente");
    } catch (MiException ex) {
      redirectAttributes.addFlashAttribute("errorBasica", ex.getMessage());
      redirectAttributes.addFlashAttribute("nombre", proveedor.getNombre());
      redirectAttributes.addFlashAttribute("email", proveedor.getEmail());
      return "redirect:/modificar";
    }
    return "redirect:/modificar";
  }

}
