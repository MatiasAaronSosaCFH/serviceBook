package com.servicebook.models;

import java.util.Collection;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "proveedores")
public class Proveedor extends Usuario {


    @Column(name = "email_de_contacto", nullable = false)
    private String emailDeContacto;
    
    @Column(name = "numero_de_contacto", nullable = false)
    private String numeroDeContacto;
    
    @ManyToMany
    @JoinColumn(name = "proveedor_profesion_id", referencedColumnName = "id")
    private List<Profesion> profesiones;
    
    @Column(name="presentacion")
    private String presentacion;
    
    @Column(name="precio_por_hora")
    private Integer precioPorHora;

    @OneToOne
    @JoinColumn(name = "foto_id", referencedColumnName = "id")
    private FotoUsuario foto;
     
    @Column(name = "disponible")
    private Boolean disponible;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Trabajo> trabajosRealizados;
}
