package com.pds.project.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "concesionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Concesionario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idConcesionaria;

   @Column(name = "nombre", length = 100, nullable = false)
   private String nombre;

   @Column(name = "cuit", length = 13, nullable = false, unique = true)
   private String cuit;

   @Column(name = "direccion", length = 255, nullable = false)
   private String direccion;
}
