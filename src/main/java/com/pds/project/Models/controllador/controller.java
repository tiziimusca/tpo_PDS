package com.pds.project.Models.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pds.project.Models.Comprador;
import com.pds.project.Models.repositorio.repositorioComprador;

@RestController
public class controller {

    @Autowired
    private repositorioComprador repoComprador;

    @GetMapping()
    public String index() {
        return "CONECTADO";
    }

    @GetMapping("/compradores")
    public List <Comprador> getCompradores() {
        return repoComprador.findAll();
    }

    @PostMapping("guaredarComprador")
    public String post(@RequestBody Comprador comprador ){
        repoComprador.save(comprador);
        return "Guardado";
    }

    @PutMapping("actualizarComprador/{id}")
    public String update(@PathVariable Long id, @RequestBody Comprador comprador){
        Comprador updateComprador = repoComprador.findById(id).get();
        updateComprador.setNombre(comprador.getNombre());
        updateComprador.setApellido(comprador.getApellido());  
        updateComprador.setDocumento(comprador.getDocumento());
        updateComprador.setDomicilio(comprador.getDomicilio());
        updateComprador.setTelefono(comprador.getTelefono());
        updateComprador.setEmail(comprador.getEmail());
        updateComprador.setCuitCuil(comprador.getCuitCuil());
        repoComprador.save(updateComprador);
        return "Actualizado";
    }

    @DeleteMapping("borrarComprador/{id}")
    public String delete(@PathVariable Long id){
        repoComprador.deleteById(id);
        return "Borrado";
    }

}
