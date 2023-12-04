
package com.servicebook.models.dtos;

import com.servicebook.models.Foto;
import com.servicebook.models.Trabajo;

public record FotoDtoEnviado(Long id,
							String nombre,
							String url,
							Long trabajo,
							String fotoId
	) {

	public FotoDtoEnviado(Foto foto) {
		this(foto.getId(),
			foto.getNombre(),
			foto.getUrl(),
			foto.getTrabajo().getId(),
			foto.getFotoId()
		);
	}
	
	
}
