package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pds.project.Models.Comprador;
import com.pds.project.Repository.ICompradorRepository;
import com.pds.project.ServiceInterface.ICompradorService;

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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Comprador getCompradorById(Long id) {
        return repoComprador.findById(id).orElse(null);
    }
    

    @Override
    public boolean eliminarComprador(Long id) {
        try{
            repoComprador.deleteById(id);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
