package com.pds.project.controllador;

import com.pds.project.Models.*;
import com.pds.project.Repository.IEstadoRepository;
import com.pds.project.Repository.IPedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/informe")
public class InformeController {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IEstadoRepository estadoRepository;

    @GetMapping("/generar")
    @Operation(summary = "Generar informe CSV de pedidos", description = "Permite generar un informe de pedidos con filtros opcionales por fecha y estado")
    @ApiResponse(responseCode = "200", description = "Informe generado correctamente")
    public ResponseEntity<String> generarInforme(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            @RequestParam(required = false) String estado,
            @RequestParam(defaultValue = "informe.csv") String archivo) {
        List<Pedido> pedidos = pedidoRepository.findAll();

        // Filtrar por fecha
        if (desde != null) {
            pedidos.removeIf(p -> p.getFechaCreacion().isBefore(desde));
        }
        if (hasta != null) {
            pedidos.removeIf(p -> p.getFechaCreacion().isAfter(hasta));
        }

        // Filtrar por estado actual
        if (estado != null && !estado.isBlank()) {
            List<Long> pedidosConEstado = estadoRepository.findByEtapa(estado).stream()
                    .map(Estado::getPedidoId)
                    .toList();

            pedidos.removeIf(p -> !pedidosConEstado.contains(p.getNumeroPedido()));
        }

        // Crear filas para el CSV
        List<String[]> filas = new ArrayList<>();
        for (Pedido p : pedidos) {
            String estadoActual = estadoRepository.findTopByPedidoIdOrderByFechaDesc(p.getNumeroPedido())
                    .map(Estado::getEtapa)
                    .orElse("Sin estado");

            filas.add(new String[] {
                    p.getNumeroPedido().toString(),
                    p.getFechaCreacion().toString(),
                    p.getVehiculoId().toString(),
                    p.getCompradorId().toString(),
                    estadoActual
            });
        }

        // Ejecutar Facade
        InformeFacade.escribirCSV(archivo, ConcesionarioSingleton.getConcesionario(), filas);

        return ResponseEntity.ok("Informe generado en: " + archivo);
    }
}
