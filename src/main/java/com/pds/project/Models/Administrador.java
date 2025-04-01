package com.pds.project.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administradores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    @Column(name = "nombre_apellido", length = 100, nullable = false)
    private String nombreApellido;

    @Column(name = "email", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;
}
