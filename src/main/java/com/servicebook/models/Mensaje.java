/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.models;

import com.servicebook.models.enums.TipoMensaje;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author SantiagoPaguaga
 */
@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mensajes")
public class Mensaje {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "texto")
    private String texto;
    
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMensaje tipo;
    
    @Column(name = "alta")
    private Boolean alta;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajo_id")
    private Trabajo trabajo;
    
}
