package com.servicebook.models;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "direcciones")
public class Direccion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "calle")
  private String calle;

  @Column(name = "numero")
  private String numero;

  @Column(name = "localidad")
  private String localidad;

  @Column(name = "provincia")
  private String provincia;

  @Column(name = "alta")
  private Boolean alta = true;

  @ManyToMany(mappedBy = "direcciones",cascade = CascadeType.REMOVE)
  private List<Proveedor> proveedores = new ArrayList<>();

  @ManyToMany(mappedBy = "direcciones",cascade = CascadeType.REMOVE)
  private List<Cliente> clientes = new ArrayList<>();

  public List<Usuario> getUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();

    if (this.proveedores != null) {
      usuarios.addAll(this.proveedores);
    }

    if (this.clientes != null) {
      usuarios.addAll(this.clientes);
    }

    return usuarios;
  }
}
