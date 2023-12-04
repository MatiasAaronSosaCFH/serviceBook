/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicebook.models;


import javax.persistence.*;

import com.servicebook.models.enums.Estrellas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calificaciones")

public class Calificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    
    private Long id;
    
    @Column(name = "estrellas")
    @Enumerated(EnumType.ORDINAL)
    private Estrellas estrellas;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajo_id", referencedColumnName = "id")
    private Trabajo trabajo;
    
    @Column(name = "alta")
    private Boolean alta;

    @Column(name = "descripcion",columnDefinition = "TEXT")
    private String descripcion;


    
}
