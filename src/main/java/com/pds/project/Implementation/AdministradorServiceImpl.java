package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.Administrador;
import com.pds.project.Repository.IAdministradorRepository;
import com.pds.project.ServiceInterface.IAdministradorService;

@Service
public class AdministradorServiceImpl implements IAdministradorService {

    @Autowired
    private IAdministradorRepository repoAdministrador;

    @Override
    public List<Administrador> getAdministradores() {
        return repoAdministrador.findAll();
    }

    public enum ResultadoAdministrador {
        OK,
        EMAIL_DUPLICADO,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoAdministrador guardarAdministrador(Administrador administrador) {

        if (repoAdministrador.existsByEmail(administrador.getEmail())) {
            return ResultadoAdministrador.EMAIL_DUPLICADO;
        }

        try {
            repoAdministrador.save(administrador);
            return ResultadoAdministrador.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoAdministrador.ERROR_DESCONOCIDO;
        }
    }

    @Override
    public Administrador getAdministradorById(Long id) {
        return repoAdministrador.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarAdministrador(Long id) {
        if (repoAdministrador.existsById(id)) {
            repoAdministrador.deleteById(id);
            return true;
        }
        return false; // Retorna false si el comprador no existía
    }

    @Override
    public ResultadoAdministrador actualizarAdministrador(long id, Administrador nuevosDatos) {
        Administrador administradorExistente = repoAdministrador.findById(id).orElse(null);

        try {
            administradorExistente.setNombreApellido(nuevosDatos.getNombreApellido());
            administradorExistente.setEmail(nuevosDatos.getEmail());
            administradorExistente.setContraseña(nuevosDatos.getContraseña());
            repoAdministrador.save(administradorExistente);
            return ResultadoAdministrador.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoAdministrador.ERROR_DESCONOCIDO;
        }

    }
}
