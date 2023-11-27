
package com.servicebook.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author julip
 */


@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="proveedores")
public class Proveedor extends Usuario {
    
    @Column(name="num_de_contacto")
    private String numDeContacto;
    
    @Column(name="email_de_contacto")
    private String emailDeContacto;
    
    @Column(name="profesion")
    @ManyToMany
    private List<Profesion> profesiones;
    
    @Column(name="presentacion")
    private String presentacion;
    
    @Column(name="precio_por_hora")
    private Integer precioPorHora;
    
    @Column(name="disponible")
    private Boolean disponible;
    
    private List<Trabajo> trabajos;
    
    
    
}
