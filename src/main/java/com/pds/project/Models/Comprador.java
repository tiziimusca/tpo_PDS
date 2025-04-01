package com.pds.project.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compradores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comprador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprador;

    @Column(name = "nombre_apellido", length = 100, nullable = false)
    private String nombreApellido;

    @Column(name = "documento", length = 11, unique = true, nullable = false)
    private String documento;

    @Column(name = "domicilio", length = 100, nullable = false)
    private String domicilio;

    @Column(name = "telefono", length = 20, unique = true, nullable = false)
    private String telefono;

    @Column(name = "email", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "cuit_cuil", length = 13, unique = true, nullable = true)
    private String cuitCuil;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;
}
