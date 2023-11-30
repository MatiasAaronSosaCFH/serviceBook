
package com.servicebook.models.dtos;

import com.servicebook.models.Usuario;
import java.util.List;
import javax.validation.constraints.NotBlank;


public record UsuarioDtoRecibido(@NotBlank String email, 
								@NotBlank String nombre, 
								@NotBlank String password,
								 List<DireccionDtoRecibido> direccion) {

	public UsuarioDtoRecibido(Usuario usuario) {
		this(usuario.getEmail(), 
			usuario.getNombre(), 
			usuario.getPassword(),
			usuario.getDireccion().stream().map(DireccionDtoRecibido::new).toList());
	}
	
}
