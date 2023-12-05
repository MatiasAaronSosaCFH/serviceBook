package com.servicebook.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@Table(name = "clientes")
public class Cliente extends Usuario {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Trabajo> trabajosPedidos;
    
    @OneToOne
    @JoinColumn(name = "foto_id", referencedColumnName = "id")
    private FotoUsuario foto;
    
}
