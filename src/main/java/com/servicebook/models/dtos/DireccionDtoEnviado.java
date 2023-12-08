
package com.servicebook.models.dtos;

import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;
import java.util.List;


public record DireccionDtoEnviado(Long id,
								String calle,
								String numero,
								String localidad,
								String provincia,
								List<Usuario> usuarios
								) {
	public DireccionDtoEnviado(Direccion direccion){
		this(direccion.getId(),
			direccion.getCalle(),
			direccion.getNumero(),
			direccion.getLocalidad(),
			direccion.getProvincia(),
			direccion.getUsuarios());
	}
}
