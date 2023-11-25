package com.servicebook.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "proveedores")
public class Proveedor extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email_de_contacto", nullable = false)
    private String emailDeContacto;
    @Column(name = "numero_de_contacto", nullable = false)
    private String numeroDeContacto;
    @Column(name = "ocupado")
    private Boolean ocupado;
    @ManyToOne
    private List<Profesion> profesiones;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Trabajo> trabajosRealizados;

}
