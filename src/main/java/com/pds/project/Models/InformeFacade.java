package com.pds.project.Models;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InformeFacade {

    public static void generarInformeCSV(List<Pedido> pedidos, String rutaArchivo) {
        Concesionario concesionario = ConcesionarioSingleton.getConcesionario();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            // Info del concesionario
            writer.append("Información del Concesionario\n");
            writer.append("Nombre: ").append(concesionario.getNombre()).append("\n");
            writer.append("CUIT: ").append(concesionario.getCuit()).append("\n");
            writer.append("Dirección: ").append(concesionario.getDireccion()).append("\n\n");

            // Encabezado de pedidos
            writer.append("ID Pedido,Fecha,Marca,Modelo,Nombre Cliente\n");

            // Detalles de cada pedido
            for (Pedido pedido : pedidos) {
                writer.append(pedido.getNumeroPedido().toString()).append(",")
                        .append(pedido.getFechaCreacion().toString()).append(",")
                        .append(pedido.getVehiculoId().toString()).append(",")
                        .append(pedido.getCompradorId().toString()).append("\n");
            }

            System.out.println("Informe generado exitosamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.out.println("Error al generar el informe: " + e.getMessage());
        }
    }
}
