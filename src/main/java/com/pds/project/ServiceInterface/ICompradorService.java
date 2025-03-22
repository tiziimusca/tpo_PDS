package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Models.Comprador;


public interface ICompradorService {

    public List<Comprador> getCompradores();
    public boolean guardarComprador(Comprador comprador);
    public Comprador getCompradorById(Long id);
    public boolean eliminarComprador(Long id);

}
