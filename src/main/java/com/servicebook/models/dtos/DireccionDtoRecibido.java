
package com.servicebook.models.dtos;

import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;
import javax.validation.constraints.NotBlank;


public record DireccionDtoRecibido(@NotBlank String calle,
								@NotBlank String numero,
								@NotBlank String localidad,
								@NotBlank String provincia
								) {
	
	public DireccionDtoRecibido(Direccion direccion){
		this(direccion.getCalle(),
			direccion.getNumero(),
			direccion.getLocalidad(),
			direccion.getProvincia()
			);
	}



//	public DireccionDtoRecibido(String calle, String numero, String localidad, String provincia, Boolean alta, Usuario usuario) {
//		this.calle = calle;
//		this.numero = numero;
//		this.localidad = localidad;
//		this.provincia = provincia;
//		this.alta = alta;
//		this.usuario = usuario;
//	}
}
