package com.pds.project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Vendedores")
public class Vendedor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idVendedor;

    @Column(name = "Nombre", length = 60)
    protected String nombre;

    @Column(name = "Correo Electronico", length = 60)
    protected String correoelectronico;
}
