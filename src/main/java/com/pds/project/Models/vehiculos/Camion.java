package com.pds.project.Models.vehiculos;

import com.pds.project.Models.Vehiculo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(Camion.DISCRIMINATOR)
@NoArgsConstructor
public class Camion extends Vehiculo {

    public static final String DISCRIMINATOR = "CAMION";

    public Camion(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precio,
            String estado) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precio, estado);
    }
}
