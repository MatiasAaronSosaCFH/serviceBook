
package com.servicebook.models;

import com.servicebook.models.enums.Role;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
@MappedSuperclass
public abstract class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;
	@Column(name = "email", nullable = false, unique = true)
	protected String email;
	@Column(name = "nombre", nullable = false)
	protected String nombre;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_de_alta", nullable = false)
	protected Date fechaDeAlta;
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	protected Role role;
	@Column(name = "alta")
	protected Boolean alta = true;
	@Column(name = "password")
	protected String password;
        @OneToOne
        @JoinColumn(name = "foto_id", referencedColumnName = "id")
        private FotoUsuario foto;
}
