/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "foto_proveedor")
public class FotoProveedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "url")
  private String url;

  @Column(name = "alta")
  private Boolean alta = true;

  @Column(name = "foto_id")
  private String fotoId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "proveedor_id")
  private Proveedor proveedor;

}
