package com.pds.project.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pds.project.Implementation.EstadoServiceImpl.ResultadoEstado;
import com.pds.project.Models.Estado;
import com.pds.project.ServiceInterface.IEstadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/admin/estados") // Define un prefijo común para todas las rutas
public class EstadoController {

    @Autowired
    private IEstadoService estadoService;

    @GetMapping
    @Operation(summary = "Obtener la lista de estados")
    @ApiResponse(responseCode = "200", description = "Lista de estados obtenida correctamente")
    public ResponseEntity<List<Estado>> listarEstados() {
        List<Estado> estados = estadoService.getEstados();
        return ResponseEntity.ok(estados);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un estado")
    @ApiResponse(responseCode = "200", description = "Estado guardado con éxito.")
    @ApiResponse(responseCode = "400", description = "No se pudieron guardar los datos del estado.")
    public ResponseEntity<String> guardarEstado(@RequestBody Estado estado) {
        ResultadoEstado resultado = estadoService.guardarEstado(estado);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Estado guardado con éxito.");
            case PEDIDO_ETAPA_DUPLICADO -> ResponseEntity.badRequest()
                    .body("Ya existe un estado con la misma etapa para el pedido.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió un error al guardar el estado.");
        };
    }

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener un estado por ID")
    @ApiResponse(responseCode = "200", description = "Estado encontrado")
    @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    public ResponseEntity<Estado> obtenerEstado(@PathVariable("id") long id) {
        Estado estado = estadoService.getEstadoById(id);

        if (estado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(estado);
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar un estado existente")
    @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    public ResponseEntity<String> editarEstado(@PathVariable("id") long id,
            @RequestBody Estado datosActualizados) {
        ResultadoEstado resultado = estadoService.actualizarEstado(id, datosActualizados);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Estado guardado con éxito.");
            case PEDIDO_ETAPA_DUPLICADO -> ResponseEntity.badRequest()
                    .body("Ya existe un estado con la misma etapa para el pedido.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió otro error al guardar el estado.");
        };
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un estado por ID")
    @ApiResponse(responseCode = "200", description = "Estado eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    public ResponseEntity<String> eliminarEstado(@PathVariable("id") long id) {
        boolean eliminado = estadoService.eliminarEstado(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar el estado con ID " + id);
        }

        return ResponseEntity.ok("Estado eliminado correctamente.");
    }
}
