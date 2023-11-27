package com.servicebook.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "proveedores")
public class Proveedor extends Usuario{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)    CLIENTE Y PROVEEDOR YA TIENEN ID HEREDADO DE USUARIO
//    @Column(name = "id")
//    private Long id;
    @Column(name = "email_de_contacto", nullable = false)
    private String emailDeContacto;
    
    @Column(name = "numero_de_contacto", nullable = false)
    private String numeroDeContacto;
    
    @ManyToMany // REVISAR RELACION
    private List<Profesion> profesiones;
    
    @Column(name="presentacion")
    private String presentacion;
    
    @Column(name="precio_por_hora")
    private Integer precioPorHora;
     
    @Column(name = "disponible")
    private Boolean disponible;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Trabajo> trabajosRealizados;
}
