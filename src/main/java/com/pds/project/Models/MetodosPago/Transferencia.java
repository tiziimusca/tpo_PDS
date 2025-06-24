package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.Pago;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(Transferencia.DISCRIMINATOR)
public class Transferencia extends Pago {

    public static final String DISCRIMINATOR = "TRANSFERENCIA";

    public Transferencia(double monto, LocalDate fechaPago, Long pedidoId) {
        super(monto, fechaPago, pedidoId);
    }
}
