package com.pds.project.Models;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected  String color;
    protected Integer numeroChasis;
    protected Integer numeroMotor;
    protected double precioBase;

    public Vehiculo(String marca, String modelo, String color, Integer numeroChasis, Integer numeroMotor, double precioBase) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precioBase = precioBase;
    }
}
