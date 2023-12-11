package com.servicebook.models.dtos;

import com.servicebook.models.Calificacion;
import com.servicebook.models.Cliente;
import com.servicebook.models.Foto;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;

import java.util.List;

public record TrabajoDtoEnviado (Long id,
								Proveedor proveedor,
								ClienteDtoEnviado cliente,
								Boolean terminoCliente,
								Boolean terminoProveedor,
								Integer calificacion
								){

	public TrabajoDtoEnviado(Trabajo trabajo) {
		this(trabajo.getId(),
			trabajo.getProveedor(),
			new ClienteDtoEnviado(trabajo.getCliente()),
			trabajo.getTerminoCliente(),
			trabajo.getTerminoProveedor(),
			trabajo.getCalificacion().getEstrellas().getNumero()
		);
	}
}
