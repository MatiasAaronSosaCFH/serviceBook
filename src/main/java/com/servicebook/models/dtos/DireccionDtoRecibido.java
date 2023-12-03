
package com.servicebook.models.dtos;

import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;
import javax.validation.constraints.NotBlank;


public record DireccionDtoRecibido(@NotBlank String calle,
								@NotBlank String numero,
								@NotBlank String localidad,
								@NotBlank String provincia,
								@NotBlank Long usuario,
								@NotBlank String role) {

}
