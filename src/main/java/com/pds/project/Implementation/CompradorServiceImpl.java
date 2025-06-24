package com.pds.project.Implementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.Comprador;
import com.pds.project.Repository.ICompradorRepository;
import com.pds.project.ServiceInterface.ICompradorService;

@Service
public class CompradorServiceImpl implements ICompradorService {

    @Autowired
    private ICompradorRepository repoComprador;

    @Override
    public List<Comprador> getCompradores() {
        return repoComprador.findAll();
    }

    public enum ResultadoComprador {
        OK,
        EMAIL_DUPLICADO,
        DOCUMENTO_DUPLICADO,
        TELEFONO_DUPLICADO,
        CUITCUIL_DUPLICADO,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoComprador guardarComprador(Comprador comprador) {

        if (repoComprador.existsByEmail(comprador.getEmail())) {
            return ResultadoComprador.EMAIL_DUPLICADO;
        }

        if (repoComprador.existsByDocumento(comprador.getDocumento())) {
            return ResultadoComprador.DOCUMENTO_DUPLICADO;
        }

        if (repoComprador.existsByTelefono(comprador.getTelefono())) {
            return ResultadoComprador.TELEFONO_DUPLICADO;
        }
        if (repoComprador.existsByCuitCuil(comprador.getCuitCuil())) {
            return ResultadoComprador.CUITCUIL_DUPLICADO;
        }
        try {
            repoComprador.save(comprador);
            return ResultadoComprador.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoComprador.ERROR_DESCONOCIDO;
        }
    }

    @Override
    public Comprador getCompradorById(Long id) {
        return repoComprador.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarComprador(Long id) {
        if (repoComprador.existsById(id)) {
            repoComprador.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ResultadoComprador actualizarComprador(long id, Comprador nuevosDatos) {
        Comprador compradorExistente = repoComprador.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el comprador con ID " + id));

        try {
            compradorExistente.setNombreApellido(nuevosDatos.getNombreApellido());
            compradorExistente.setDocumento(nuevosDatos.getDocumento());
            compradorExistente.setEmail(nuevosDatos.getEmail());
            compradorExistente.setTelefono(nuevosDatos.getTelefono());
            compradorExistente.setCuitCuil(nuevosDatos.getCuitCuil());
            compradorExistente.setDomicilio(nuevosDatos.getDomicilio());
            compradorExistente.setContraseña(nuevosDatos.getContraseña());
            repoComprador.save(compradorExistente);
            return ResultadoComprador.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoComprador.ERROR_DESCONOCIDO;
        }

    }
}
