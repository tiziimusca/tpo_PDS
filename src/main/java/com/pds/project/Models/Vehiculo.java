package com.pds.project.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo", discriminatorType = DiscriminatorType.STRING)
@Table(name = "vehiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    @Column(name = "marca", length = 50, nullable = false)
    private String marca;

    @Column(name = "modelo", length = 50, nullable = false)
    private String modelo;

    @Column(name = "color", length = 30, nullable = false)
    private String color;

    @Column(name = "numero_chasis", unique = true, nullable = false, length = 50)
    private String numeroChasis;

    @Column(name = "numero_motor", unique = true, nullable = false, length = 50)
    private String numeroMotor;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "estado", nullable = false)
    private String estado;

    public Vehiculo(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precio,
            String estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precio = precio;
        this.estado = estado;
    }
}
