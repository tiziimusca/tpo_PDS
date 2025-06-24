package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.Vendedor;
import com.pds.project.Repository.IVendedorRepository;
import com.pds.project.ServiceInterface.IVendedorService;

@Service
public class VendedorServiceImpl implements IVendedorService {

    @Autowired
    private IVendedorRepository repoVendedor;

    @Override
    public List<Vendedor> getVendedores() {
        return repoVendedor.findAll();
    }

    public enum ResultadoVendedor {
        OK,
        EMAIL_DUPLICADO,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoVendedor guardarVendedor(Vendedor vendedor) {
        if (repoVendedor.existsByEmail(vendedor.getEmail())) {
            return ResultadoVendedor.EMAIL_DUPLICADO;
        }
        try {
            repoVendedor.save(vendedor);
            return ResultadoVendedor.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoVendedor.ERROR_DESCONOCIDO;
        }
    }

    @Override
    public Vendedor getVendedorById(Long id) {
        return repoVendedor.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarVendedor(Long id) {
        if (repoVendedor.existsById(id)) {
            repoVendedor.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ResultadoVendedor actualizarVendedor(long id, Vendedor nuevosDatos) {
        Vendedor vendedorExistente = repoVendedor.findById(id).orElse(null);

        try {
            vendedorExistente.setNombreApellido(nuevosDatos.getNombreApellido());
            vendedorExistente.setEmail(nuevosDatos.getEmail());
            vendedorExistente.setContraseña(nuevosDatos.getContraseña());
            repoVendedor.save(vendedorExistente);
            return ResultadoVendedor.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoVendedor.ERROR_DESCONOCIDO;
        }
    }
}
