package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.Pago;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(Transferencia.DISCRIMINATOR)
@NoArgsConstructor
public class Transferencia extends Pago {

    public static final String DISCRIMINATOR = "TRANSFERENCIA";

}
