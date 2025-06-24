package com.pds.project.Models;

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
