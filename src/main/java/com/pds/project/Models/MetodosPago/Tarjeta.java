package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.MetodoPago;
import com.pds.project.Models.Vehiculo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TARJETA")
public class Tarjeta extends MetodoPago {
    public Tarjeta() {}

    @Override
    public double pagar( Vehiculo vehiculo) {
        double monto = vehiculo.getPrecio() * 0.95;
        return monto;
    }
}
