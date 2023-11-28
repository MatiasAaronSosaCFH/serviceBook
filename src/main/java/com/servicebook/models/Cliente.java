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
@Data
@Table(name = "clientes")
public class Cliente extends Usuario {

    @OneToMany
    private List<Trabajo> trabajosPedidos;


}
