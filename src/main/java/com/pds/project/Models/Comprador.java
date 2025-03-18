package com.pds.project.Models;

public class Comprador {
    protected String nombre;
    protected String apellido;
    protected String documento;
    protected String telefono;
    protected String email;

    public Comprador(String nombre, String apellido, String documento, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.email = email;
    }
}
