package com.servicebook.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profesiones")
public class Profesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "alta")
    private Boolean alta = true;
    @ManyToMany
    @JoinColumn(name = "proveedor_profesion_id", referencedColumnName = "id")
    private List<Proveedor> proveedores;
}
