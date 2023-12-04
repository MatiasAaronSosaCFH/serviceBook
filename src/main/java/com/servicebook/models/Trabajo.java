package com.servicebook.models;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trabajos")
public class Trabajo{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proveedor_id", nullable=false)
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;

    @Column(name="termino_cliente")
    private Boolean terminoCliente;
    
    @Column(name="termino_proveedor")
    private Boolean terminoProveedor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="calificacion_id", referencedColumnName = "id")
    private Calificacion calificacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajo")
    private List<Foto> fotos;
    
    @Column(name="alta")
    private Boolean alta = true;
    
}
