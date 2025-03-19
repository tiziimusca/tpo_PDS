package com.pds.project.Models;
import jakarta.persistence.Entity;

@Entity
public class Concesionario {
   protected String nombre;
   protected Integer cuit; 
   protected String direccion;
}
