package com.pds.project.Models;

import com.pds.project.Models.Concesionario;

public class ConcesionarioSingleton {
    private static Concesionario concesionario;

    private ConcesionarioSingleton() {
    }

    public static void setConcesionario(Concesionario nuevoConcesionario) {
        if (concesionario == null) {
            concesionario = nuevoConcesionario;
        }
    }

    public static Concesionario getConcesionario() {
        return concesionario;
    }
}
