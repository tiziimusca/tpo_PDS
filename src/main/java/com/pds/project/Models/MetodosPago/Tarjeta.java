package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.Pago;
import com.pds.project.Models.Vehiculo;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(Tarjeta.DISCRIMINATOR)
@NoArgsConstructor
public class Tarjeta extends Pago {

    public static final String DISCRIMINATOR = "TARJETA_CREDITO";

    @Override
    public double pagar(Vehiculo vehiculo) {
        return vehiculo.getPrecio();
    }
}
