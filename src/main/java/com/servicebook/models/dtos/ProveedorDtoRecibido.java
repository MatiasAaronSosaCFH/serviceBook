
package com.servicebook.models.dtos;

import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record ProveedorDtoRecibido(@NotBlank String emailDeContacto,
								@NotBlank String numeroDeContacto,
								@NotNull List<ProfesionDtoRecibido> profesiones,
								List<TrabajoDtoRecibido> trabajosRealizados	
								) {
	
	public ProveedorDtoRecibido(Proveedor proveedor){
		this(proveedor.getEmailDeContacto(),
			proveedor.getNumeroDeContacto(),
			proveedor.getProfesiones().stream().map(ProfesionDtoRecibido::new).toList(),
			proveedor.getTrabajosRealizados().stream().map(TrabajoDtoRecibido::new).toList()
		);
	}

}
