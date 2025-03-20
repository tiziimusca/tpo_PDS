package com.pds.project.Implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.Vendedor;
import com.pds.project.Repository.IVendedorRepository;
import com.pds.project.ServiceInterface.IVendedorService;

@Service
public class VendedorServiceImpl implements IVendedorService{

    @Autowired
    private IVendedorRepository repoVendedor;

    @Override
    public List<Vendedor> getVendedores() {
        return repoVendedor.findAll();
    }
}
