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

import com.pds.project.Implementation.ConfiguracionAdicionalServiceImpl.ResultadoConfiguracionAdicional;
import com.pds.project.Models.ConfiguracionAdicional;
import com.pds.project.ServiceInterface.IConfiguracionAdicionalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/admin/configuraciones_adicionales") // Define un prefijo común para todas las rutas
public class ConfiguracionAdicionalController {

    @Autowired
    private IConfiguracionAdicionalService configuracionAdicionalService;

    @GetMapping
    @Operation(summary = "Obtener la lista de configuraciones adicionales")
    @ApiResponse(responseCode = "200", description = "Lista de configuraciones adicionales obtenida correctamente")
    public ResponseEntity<List<ConfiguracionAdicional>> listarConfiguracionesAdicional() {
        List<ConfiguracionAdicional> configuraciones = configuracionAdicionalService.getConfiguracionesAdicional();
        return ResponseEntity.ok(configuraciones);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar una configuracion adicional")
    @ApiResponse(responseCode = "200", description = "Configuracion adicional guardada con éxito.")
    @ApiResponse(responseCode = "400", description = "No se pudieron guardar los datos de la configuracion adicional.")
    public ResponseEntity<String> guardarConfiguracionAdicional(@RequestBody ConfiguracionAdicional configuracion) {
        ResultadoConfiguracionAdicional resultado = configuracionAdicionalService
                .guardarConfiguracionAdicional(configuracion);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Configuracion adicional guardada con éxito.");
            case CONFIG_DUPLICADA ->
                ResponseEntity.badRequest().body("Ya existe una configuracion adicional asignada a ese pedido.");
            case ERROR_DESCONOCIDO ->
                ResponseEntity.badRequest().body("Ocurrió un error al guardar la configuracion adicional.");
        };
    }

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener una configuracion adicional por ID")
    @ApiResponse(responseCode = "200", description = "Configuracion adicional encontrada")
    @ApiResponse(responseCode = "404", description = "Configuracion adicional no encontrada")
    public ResponseEntity<ConfiguracionAdicional> obtenerConfiguracionAdicional(@PathVariable("id") long id) {
        ConfiguracionAdicional config = configuracionAdicionalService.getConfiguracionAdicionalById(id);

        if (config == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(config);
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar una configuracion adicional existente")
    @ApiResponse(responseCode = "200", description = "Configuracion adicional actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Configuracion adicional no encontrada")
    public ResponseEntity<String> editarConfiguracionAdicional(@PathVariable("id") long id,
            @RequestBody ConfiguracionAdicional datosActualizados) {
        ResultadoConfiguracionAdicional resultado = configuracionAdicionalService.actualizarConfiguracionAdicional(id,
                datosActualizados);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Configuracion adicional guardada con éxito.");
            case CONFIG_DUPLICADA ->
                ResponseEntity.badRequest().body("Ya existe una configuracion adicional asignada a ese pedido.");
            case ERROR_DESCONOCIDO ->
                ResponseEntity.badRequest().body("Ocurrió un error al guardar la configuracion adicional.");
        };
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar una configuracion adicional por ID")
    @ApiResponse(responseCode = "200", description = "Configuracion adicional eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Configuracion adicional no encontrada")
    public ResponseEntity<String> eliminarConfiguracionAdicional(@PathVariable("id") long id) {
        boolean eliminado = configuracionAdicionalService.eliminarConfiguracionAdicional(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar la configuracion adicional con ID " + id);
        }

        return ResponseEntity.ok("Configuracion adicional eliminada correctamente.");
    }
}
