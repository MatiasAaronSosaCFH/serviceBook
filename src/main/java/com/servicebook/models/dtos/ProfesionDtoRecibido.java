
package com.servicebook.models.dtos;

import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import javax.validation.constraints.NotBlank;


public record ProfesionDtoRecibido(@NotBlank String nombre,
								@NotBlank Proveedor proveedor
								) {

	public ProfesionDtoRecibido(Profesion profesion){
		this(profesion.getNombre(),
			profesion.getProveedor() 
		);
	}
	
}
