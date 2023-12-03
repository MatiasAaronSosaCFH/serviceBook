
package com.servicebook.models.dtos;

import com.servicebook.models.Foto;
import com.servicebook.models.Trabajo;
import javax.validation.constraints.NotBlank;


public record FotoDtoRecibido(@NotBlank String nombre,
							@NotBlank String url,
							@NotBlank Long trabajo,
							@NotBlank String fotoId
							) {

	public FotoDtoRecibido(Foto foto) {
		this(foto.getNombre(),
			foto.getUrl(),
			foto.getTrabajo().getId(),
			foto.getFotoId()
		);
	}
	
}
