package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.Pago;
import com.pds.project.Models.Vehiculo;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(Transferencia.DISCRIMINATOR)
@NoArgsConstructor
public class Transferencia extends Pago {

    public static final String DISCRIMINATOR = "TRANSFERENCIA";

    @Override
    public double pagar(Vehiculo vehiculo) {
        return vehiculo.getPrecio();
    }
}
