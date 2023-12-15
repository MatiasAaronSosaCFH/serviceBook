package com.servicebook.models.dtos;

import com.servicebook.models.FotoUsuario;
import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import java.util.Date;
import java.util.List;

public record ProveedorDtoEnviado(Long id,
        String nombre,
        FotoUsuario foto,
        String email,
        List<DireccionDtoEnviado> direcciones,
        Date fechaDeAlta,
        String role,
        String emailDeContacto,
        String numeroDeContacto,
        List<ProfesionDtoEnviado> profesiones,
        String presentacion,
        Integer precioPorHora,
        Boolean disponible
	) {

  public ProveedorDtoEnviado(Proveedor proveedor) {
    this(proveedor.getId(),
            proveedor.getNombre(),
            proveedor.getFoto(),
            proveedor.getEmail(),
            proveedor.getDirecciones().stream().map(DireccionDtoEnviado::new).toList(),
            proveedor.getFechaDeAlta(),
            proveedor.getRole().toString(),
            proveedor.getEmailDeContacto(),
            proveedor.getNumeroDeContacto(),
            proveedor.getProfesiones().stream().map(ProfesionDtoEnviado::new).toList(),
            proveedor.getPresentacion(),
            proveedor.getPrecioPorHora(),
            proveedor.getDisponible()
	 );
  }

}
