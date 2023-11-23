package com.servicebook.models;

import java.util.List;
import javax.persistence.*;

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
    private Long id; 

    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="proveedor_id", nullable=false)
    //private Proveedor proveedor;
    
    @Column(name="termino_cliente")
    private Boolean terminoCliente;
    
    @Column(name="termino_proveedor")
    private Boolean terminoProveedor;

    @OneToOne
    @JoinColumn(name="calificacion_id")
    private Calificacion calificacion;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Foto> fotos;
    
    @Column(name="alta")
    private Boolean alta = true;
    
}
