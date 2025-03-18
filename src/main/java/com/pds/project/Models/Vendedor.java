package com.pds.project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendedor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idVendedor;
    @Column (name = "nombre", length=60)
    protected String nombre;
    @Column (name = "nombre", length=60)
    protected String correoelectronico;
}
