package com.pds.project.Implementation;

import java.util.List;

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

    @Override
    public boolean guardarComprador(Comprador comprador) {
        try {
            repoComprador.save(comprador);
            return true; // Retorna true si la operación fue exitosa
        } catch (Exception e) {
            return false; // Retorna false si hubo un error al guardar
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
        return false; // Retorna false si el comprador no existía
    }
}
