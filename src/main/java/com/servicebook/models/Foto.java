package com.servicebook.models;

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
@Table(name = "fotos")

public class Foto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "url")
    private String url;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajo_id", referencedColumnName = "id")
    private Trabajo trabajo;
    @Column(name = "alta")
    private Boolean alta;
    @Column(name = "foto_id")
    private String fotoId;
    
}
