package com.pds.project.Models.MetodosPago;
import com.pds.project.Models.MetodoPago;
import com.pds.project.Models.Vehiculo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONTADO")
public class Contado extends MetodoPago {
    public Contado() {}

    @Override
    public double pagar( Vehiculo vehiculo) {
        double monto = vehiculo.getPrecio() * 0.95;
        return monto;
    }
}
