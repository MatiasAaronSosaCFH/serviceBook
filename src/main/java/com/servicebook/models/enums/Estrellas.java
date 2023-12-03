package com.servicebook.models.enums;

import java.util.Objects;

public enum Estrellas {

    UNA_ESTRELLA(1),
    DOS_ESTRELLAS(2),
    TRES_ESTRELLAS(3),
    CUATRO_ESTRELLAS(4),
    CINCO_ESTRELLAS(5);

    private Integer numero;
    Estrellas(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero(){
        return numero;
    }

    public static Estrellas obtenerEstrellas(Integer numero){
        for (Estrellas estrella : values()){
            if(Objects.equals(estrella.numero, numero)){
                return estrella;
            }
        }
        throw new IllegalArgumentException("El valor ingresado no esta contemplado en la cantidad de estrellas");
    }
}
