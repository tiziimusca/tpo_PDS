package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Models.Vendedor;

public interface IVendedorService {

    public List<Vendedor> getVendedores();
    public boolean guardarVendedor(Vendedor vendedor);
    public Vendedor getVendedorById(Long id);
    public boolean eliminarVendedor(Long id);
}

