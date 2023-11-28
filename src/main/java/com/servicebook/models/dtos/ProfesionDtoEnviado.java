
package com.servicebook.models.dtos;

import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;


public record ProfesionDtoEnviado(Long id,
								String nombre,
								Boolean alta,
								Proveedor proveedor
								) {

	public ProfesionDtoEnviado(Profesion profesion){
		this(profesion.getId(), 
			profesion.getNombre(),
			profesion.getAlta(),
			profesion.getProveedor()
			);
	}


}
