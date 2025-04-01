package com.pds.project.Models.vehiculos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import com.pds.project.Models.Vehiculo;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(Auto.DISCRIMINATOR)
@NoArgsConstructor
public class Auto extends Vehiculo {

    public static final String DISCRIMINATOR = "AUTO";

    public Auto(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precio,
            String estado) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precio, estado);
    }
}
