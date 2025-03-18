package com.pds.project.Models;

public class Factura {
    protected String razonSocial;
    protected String direccion;
    protected Integer cuitCuil;

    public Factura(String razonSocial, String direccion, Integer cuitCuil) {
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.cuitCuil = cuitCuil; // si no correspondiera va null
    }
}
