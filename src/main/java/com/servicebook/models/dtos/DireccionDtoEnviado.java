
package com.servicebook.models.dtos;

import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;


public record DireccionDtoEnviado(Long id,
								String calle,
								String numero,
								String localidad,
								String provincia,
								Usuario usuario
								) {
	public DireccionDtoEnviado(Direccion direccion){
		this(direccion.getId(),
			direccion.getCalle(),
			direccion.getNumero(),
			direccion.getLocalidad(),
			direccion.getProvincia(),
			direccion.getUsuario()); // ManyToOne necesita lista???
	}

//	public DireccionDtoEnviado(Long id, String calle, String numero, String localidad, String provincia, Boolean alta, Usuario usuario) {
//		this.id = id;
//		this.calle = calle;
//		this.numero = numero;
//		this.localidad = localidad;
//		this.provincia = provincia;
//		this.alta = alta;
//		this.usuario = usuario;
//	}
	
	
}
