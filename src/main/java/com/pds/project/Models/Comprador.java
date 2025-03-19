package com.pds.project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCuitCuil() {
        return cuitCuil;
    }

    public void setCuitCuil(Integer cuitCuil) {
        this.cuitCuil = cuitCuil;
    }
}
