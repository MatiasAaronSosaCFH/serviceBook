
package com.servicebook.models.dtos;

import com.servicebook.models.Calificacion;
import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record TrabajoDtoRecibido(@NotBlank Long proveedor,
								@NotBlank Long cliente,
								@NotNull Boolean terminoCliente,
								@NotNull Boolean terminoProveedor,
								@NotBlank CalificacionDtoRecibido calificacion,
								List<FotoDtoRecibido> fotos
								) {

	
}
