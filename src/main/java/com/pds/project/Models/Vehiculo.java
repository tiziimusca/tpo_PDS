package com.pds.project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo de vehiculo", discriminatorType = DiscriminatorType.STRING)
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "modelo", length = 50)
    private String modelo;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "numero_chasis", unique = true)
    private Integer numeroChasis;

    @Column(name = "numero_motor", unique = true)
    private Integer numeroMotor;

    @Column(name = "precio")
    private double precio;

    public Vehiculo() {}

    public Vehiculo(String marca, String modelo, String color, Integer numeroChasis, Integer numeroMotor, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precio = precio;
    }
}
