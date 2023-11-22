
package com.servicebook.models;

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
    @Column(name = "id")
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")   
    private Usuario usuario;
    
}
