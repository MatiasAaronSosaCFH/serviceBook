package com.servicebook.controller;

import com.servicebook.models.Cliente;
import com.servicebook.models.FotoProveedor;
import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
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
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
  @GetMapping("/registrar/{id}")
  public String registrarProveedor(HttpSession session, ModelMap map) {

    Cliente usuario = (Cliente) session.getAttribute("usuariosession");
    map.addAttribute("usuario", usuario);
    System.out.println(usuario.getAlta().toString() + usuario.getRole() + usuario.getFoto());
    map.addAttribute("roles", Role.values());
    List<Profesion> profesiones = profesionRepository.listarProfesiones();
    map.addAttribute("profesiones", profesiones);
    return "modificar_proveedor.html";
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
  @PostMapping("/registrar/{id}")
  public String registrarProveedor(@PathVariable Long id,
          @RequestParam String emailDeContacto,
          @RequestParam String numeroDeContacto,
          @RequestParam(required = false) List<Profesion> profesiones,
          @RequestParam String presentacion,
          @RequestParam Integer precioPorHora,
          @RequestParam Boolean disponible,
          @RequestParam("imagenes") MultipartFile[] imagenes,
          ModelMap map,
          RedirectAttributes redirectAttributes) {

    map.addAttribute("roles", Role.values());

    try {
      // Crear el proveedor y obtener la instancia
      Proveedor proveedor = proveedorService.crearProveedor(id, emailDeContacto, numeroDeContacto, profesiones, presentacion, precioPorHora, disponible);

      // Procesar y guardar las imágenes
      for (MultipartFile imagen : imagenes) {
        BufferedImage entry = ImageIO.read(imagen.getInputStream());

        if (entry == null) {
          return "redirect:/proveedor/registrar/" + id + "?error=imagen_nula";
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

      // Redireccionar a la página de éxito o a donde sea necesario
      return "redirect:/inicio";
    } catch (Exception ex) {
      // Manejar errores y redirigir con mensajes de error
      return "redirect:/proveedor/registrar/" + id + "?error=" + ex.getMessage();
    }
  }

}
