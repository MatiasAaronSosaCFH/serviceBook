
package com.servicebook.models.dtos;

import com.servicebook.models.Calificacion;
import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record TrabajoDtoRecibido(@NotBlank Proveedor proveedor,
								@NotBlank Cliente cliente,
								@NotNull Boolean terminoCliente,
								@NotNull Boolean terminoProveedor,
								Calificacion calificacion,
								List<FotoDtoEnviado> fotos
								) {

	public TrabajoDtoRecibido(Trabajo trabajo) {
		this(trabajo.getProveedor(),
			trabajo.getCliente(),
			trabajo.getTerminoCliente(),
			trabajo.getTerminoProveedor(),
			trabajo.getCalificacion(),
			trabajo.getFotos().stream().map(FotoDtoEnviado::new).toList()
		);
		
		
	}

	
}
