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

    @Override
    public boolean guardarVendedor(Vendedor vendedor) {
        try {
            repoVendedor.save(vendedor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Vendedor getVendedorById(Long id) {
        return repoVendedor.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarVendedor(Long id) {
        try {
            repoVendedor.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
