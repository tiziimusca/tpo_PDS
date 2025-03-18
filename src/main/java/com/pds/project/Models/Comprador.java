package com.pds.project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comprador {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idComprador;
    @Column (name = "nombre", length=60)
    protected String nombre;
    @Column (name = "apellido", length=60)
    protected String apellido;
    @Column (name = "documento")
    protected Integer documento;
    @Column (name = "telefono")
    protected Integer telefono;
    @Column (name = "email", length=60)
    protected String email;    
}
