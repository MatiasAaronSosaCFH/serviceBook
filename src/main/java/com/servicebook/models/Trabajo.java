package com.servicebook.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "trabajos")
public class Trabajo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @OneToOne
    private Long id; 
    
    @Column(name="proveedor", nullable=false)
    @OneToOne
    private Proveedor proveedor;
    
    @Column(name="termino_cliente")
    private Boolean termino_cliente;
    
    @Column(name="termino_proveedor")
    private Boolean termino_proveedor;
    
    @Column(name="calificacion")
    @OneToOne
    private Calificacion calificacion;
    
    private List<Foto> fotos;
    
    @Column(name="alta")
    private Boolean alta;
    
}
