package com.pds.project.Models.vehiculos;

import com.pds.project.Models.Vehiculo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue(Moto.DISCRIMINATOR)
@NoArgsConstructor
public class Moto extends Vehiculo {

    public static final String DISCRIMINATOR = "MOTO";

    public Moto(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precio,
            String estado) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precio, estado);
    }
}
