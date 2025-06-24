package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.Pago;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(Contado.DISCRIMINATOR)
public class Contado extends Pago {

    public static final String DISCRIMINATOR = "CONTADO";

    public Contado(double monto, LocalDate fechaPago, Long pedidoId) {
        super(monto, fechaPago, pedidoId);
    }
}
