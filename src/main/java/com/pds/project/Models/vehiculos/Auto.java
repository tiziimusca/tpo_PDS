package com.pds.project.Models.vehiculos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import com.pds.project.Models.Vehiculo; 

@Entity
@DiscriminatorValue("AUTO") 
public class Auto extends Vehiculo {
    public Auto() {}

    public Auto(String marca, String modelo, String color, Integer numeroChasis, Integer numeroMotor, double precio) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precio);
    }
}
