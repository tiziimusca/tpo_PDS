package com.pds.project.Models.vehiculos;

import com.pds.project.Models.Vehiculo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAMIONETA")
public class Camioneta extends Vehiculo {
    public Camioneta() {}

    public Camioneta(String marca, String modelo, String color, Integer numeroChasis, Integer numeroMotor, double precio) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precio);
    }
}