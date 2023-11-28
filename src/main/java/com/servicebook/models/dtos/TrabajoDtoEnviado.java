package com.servicebook.models.dtos;

import com.servicebook.models.Calificacion;
import com.servicebook.models.Cliente;
import com.servicebook.models.Foto;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;

import java.util.List;

public record TrabajoDtoEnviado (Long id,
								Proveedor proveedor,
								Cliente cliente,
								Boolean terminoCliente,
								Boolean terminoProveedor,
								Calificacion calificacion,
								List<FotoDtoEnviado> fotos  //revisar el DTO de fotos
								){

	public TrabajoDtoEnviado(Trabajo trabajo) {
		this(trabajo.getId(),
			trabajo.getProveedor(),
			trabajo.getCliente(),
			trabajo.getTerminoCliente(),
			trabajo.getTerminoProveedor(),
			trabajo.getCalificacion(),
		trabajo.getFotos().stream().map(FotoDtoEnviado::new).toList()
		);
	}
}
