package com.pds.project.Models;

import com.pds.project.Repository.IConcesionarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcesionarioInitializer {

    @Autowired
    private IConcesionarioRepository concesionarioRepository;

    @PostConstruct
    public void init() {
        // Asumimos que solo hay un concesionario, o tomamos el primero
        Concesionario concesionario = concesionarioRepository.findAll().stream().findFirst().orElse(null);

        if (concesionario != null) {
            ConcesionarioSingleton.setConcesionario(concesionario);
            System.out.println("Concesionario cargado en Singleton: " + concesionario.getNombre());
        } else {
            System.out.println("No se encontró ningún concesionario en la base de datos.");
        }
    }
}
