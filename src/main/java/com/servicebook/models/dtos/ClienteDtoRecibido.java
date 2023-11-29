package com.servicebook.models.dtos;
import com.servicebook.models.Cliente;

import java.util.List;
import javax.validation.constraints.NotNull;

public record ClienteDtoRecibido(@NotNull List<TrabajoDtoRecibido> trabajosPedidos) {

    public ClienteDtoRecibido(Cliente cliente){
        this(cliente.getTrabajosPedidos().stream().map(TrabajoDtoRecibido::new).toList());
    }
}
