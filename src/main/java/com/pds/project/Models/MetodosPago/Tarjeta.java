package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.Pago;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(Tarjeta.DISCRIMINATOR)
public class Tarjeta extends Pago {

    public static final String DISCRIMINATOR = "TARJETA_CREDITO";

    public Tarjeta(double monto, LocalDate fechaPago, Long pedidoId) {
        super(monto, fechaPago, pedidoId);
    }
}
