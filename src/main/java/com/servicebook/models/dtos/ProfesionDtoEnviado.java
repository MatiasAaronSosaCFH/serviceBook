
package com.servicebook.models.dtos;

import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Usuario;

import java.util.List;


public record ProfesionDtoEnviado(Long id,
								String nombre,
								Boolean alta,
								List<Long> proveedores
								) {

	public ProfesionDtoEnviado(Profesion profesion){
		this(profesion.getId(), 
			profesion.getNombre(),
			profesion.getAlta(),
			profesion.getProveedores().stream().map(Usuario::getId).toList()
			);
	}


}
