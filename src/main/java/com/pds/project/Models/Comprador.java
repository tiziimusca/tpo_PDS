package com.pds.project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Compradores")
public class Comprador {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idComprador;

    @Column(name = "Nombre", length = 60)
    protected String nombre;

    @Column(name = "Apellido", length = 60)
    protected String apellido;

    @Column(unique = true, name = "Documento")
    protected Integer documento;

    @Column(name = "Domicilio", length = 60)
    protected String domicilio;

    @Column(unique = true, name = "Telefono")
    protected Integer telefono;

    @Column(unique = true, name = "Correo Electronico", length = 60)
    protected String email;

    @Column(unique = true, name = "Cuit/Cuil")
    protected Integer cuitCuil;    
}
