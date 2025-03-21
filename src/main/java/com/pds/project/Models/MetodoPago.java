package com.pds.project.Models;

import org.springframework.stereotype.Component;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Component
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Metodo de pago", discriminatorType = DiscriminatorType.STRING)
public abstract class MetodoPago {
    public MetodoPago() {}
    public abstract double pagar(Vehiculo vehiculo);
}
