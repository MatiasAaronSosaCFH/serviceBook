/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.models.dtos;

import com.servicebook.models.FotoProveedor;
import com.servicebook.models.Profesion;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author SantiagoPaguaga
 */
@Data
@AllArgsConstructor
public class ProveedorConFotosDto {

  private Long id;
  private String nombre;
  private String presentacion;
  private Integer precioPorHora;
  private List<FotoProveedor> fotos;
  private List<Profesion> profesiones;

  public ProveedorConFotosDto() {
  }

//  public void agregarFoto(FotoProveedor foto) {
//    if (this.fotos == null) {
//      this.fotos = new ArrayList<>();
//    }
//    this.fotos.add(foto);
//  }

}
