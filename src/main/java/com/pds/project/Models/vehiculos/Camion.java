package com.pds.project.Models.vehiculos;

import com.pds.project.Models.Vehiculo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAMION")
public class Camion extends Vehiculo {
    public Camion() {}

    public Camion(String marca, String modelo, String color, Integer numeroChasis, Integer numeroMotor, double precio) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precio);
    }
}
