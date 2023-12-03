
package com.servicebook.models.dtos;

import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;


public record DireccionDtoEnviado(Long id,
								String calle,
								String numero,
								String localidad,
								String provincia,
								Long usuario
								) {
	public DireccionDtoEnviado(Direccion direccion){
		this(direccion.getId(),
			direccion.getCalle(),
			direccion.getNumero(),
			direccion.getLocalidad(),
			direccion.getProvincia(),
			direccion.orElse().getId());
	}
}
