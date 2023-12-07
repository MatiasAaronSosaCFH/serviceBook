
package com.servicebook.models.dtos;

import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;
import com.servicebook.models.enums.Role;
import java.util.Date;
import java.util.List;


public record UsuarioDtoEnviado(Long id,
								String  email,
								String nombre,
								Date fechaDeAlta,
								Role role) {

	public UsuarioDtoEnviado(Usuario usuario){
		this(usuario.getId(), 
			usuario.getEmail(),
			usuario.getNombre(),
			usuario.getFechaDeAlta(),
			usuario.getRole());
	}

	
}
