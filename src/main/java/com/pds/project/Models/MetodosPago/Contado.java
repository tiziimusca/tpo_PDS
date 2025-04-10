package com.pds.project.Models.MetodosPago;

import com.pds.project.Models.Pago;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(Contado.DISCRIMINATOR)
@NoArgsConstructor
public class Contado extends Pago {

    public static final String DISCRIMINATOR = "CONTADO";

}
