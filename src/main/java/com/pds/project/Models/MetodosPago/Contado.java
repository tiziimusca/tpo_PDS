package com.pds.project.Models.MetodosPago;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONTADO")
public class Contado {
    public Contado() {}
}
