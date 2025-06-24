package com.pds.project.Models;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InformeFacade {

    public static void escribirCSV(String rutaArchivo, Concesionario concesionario, List<String[]> filas) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.append("Información del Concesionario\n");
            writer.append("Nombre: ").append(concesionario.getNombre()).append("\n");
            writer.append("CUIT: ").append(concesionario.getCuit()).append("\n");
            writer.append("Dirección: ").append(concesionario.getDireccion()).append("\n\n");

            writer.append("ID Pedido,Fecha,ID Vehiculo,ID Comprador,Estado Actual\n");

            for (String[] fila : filas) {
                writer.append(String.join(",", fila)).append("\n");
            }

            System.out.println("Informe generado exitosamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.out.println("Error al generar el informe: " + e.getMessage());
        }
    }
}
