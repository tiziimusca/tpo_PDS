package com.pds.project.Models.MetodosPago;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TARJETA")
public class Tarjeta {
    public Tarjeta() {}
}
