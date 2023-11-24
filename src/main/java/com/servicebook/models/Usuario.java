
package com.servicebook.models;

import com.servicebook.models.enums.Role;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
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
	protected Role Role;
	@Column(name = "alta")
	protected Boolean alta = true;
	@Column(name = "password")
	protected String password;
	@OneToMany
	protected List<Direccion> direccion;


}
