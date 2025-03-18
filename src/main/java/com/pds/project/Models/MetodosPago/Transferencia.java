package com.pds.project.Models.MetodosPago;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TRANSFERENCIA")
public class Transferencia {
    public Transferencia() {}
}