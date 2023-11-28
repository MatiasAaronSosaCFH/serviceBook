package com.servicebook.models.dtos;

import com.servicebook.models.Cliente;

import java.util.List;

public record ClienteDtoEnviado(Long id,
							List<TrabajoDtoEnviado> trabajosPedidos
							) {

    public ClienteDtoEnviado(Cliente cliente){
        this(cliente.getId(),
                cliente.getTrabajosPedidos().stream().map(TrabajoDtoEnviado::new).toList()
		  );
    }
}
