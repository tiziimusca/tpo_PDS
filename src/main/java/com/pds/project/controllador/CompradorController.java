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

import com.pds.project.Implementation.CompradorServiceImpl.ResultadoComprador;
import com.pds.project.Models.Comprador;
import com.pds.project.ServiceInterface.ICompradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/compradores") // Define un prefijo común para todas las rutas
public class CompradorController {

    @Autowired
    private ICompradorService compradorService;

    @GetMapping
    @Operation(summary = "Obtener la lista de compradores")
    @ApiResponse(responseCode = "200", description = "Lista de compradores obtenida correctamente")
    public ResponseEntity<List<Comprador>> listarCompradores() {
        List<Comprador> compradores = compradorService.getCompradores();
        return ResponseEntity.ok(compradores);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un comprador")
    @ApiResponse(responseCode = "200", description = "Comprador guardado con éxito.")
    @ApiResponse(responseCode = "400", description = "No se pudieron guardar los datos del comprador.")
    public ResponseEntity<String> guardarComprador(@RequestBody Comprador comprador) {
        ResultadoComprador resultado = compradorService.guardarComprador(comprador);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Comprador guardado con éxito.");
            case EMAIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe un comprador con ese email.");
            case DOCUMENTO_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe un comprador con ese documento.");
            case TELEFONO_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe un comprador con ese teléfono.");
            case CUITCUIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe un comprador con ese CUIT/CUIL.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió un error al guardar el comprador.");
        };
    }

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener un comprador por ID")
    @ApiResponse(responseCode = "200", description = "Comprador encontrado")
    @ApiResponse(responseCode = "404", description = "Comprador no encontrado")
    public ResponseEntity<Comprador> obtenerComprador(@PathVariable("id") long id) {
        Comprador comprador = compradorService.getCompradorById(id);

        if (comprador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(comprador);
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar un comprador existente")
    @ApiResponse(responseCode = "200", description = "Comprador actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Comprador no encontrado")
    public ResponseEntity<String> editarComprador(@PathVariable("id") long id,
            @RequestBody Comprador datosActualizados) {
        ResultadoComprador resultado = compradorService.actualizarComprador(id, datosActualizados);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Comprador guardado con éxito.");
            case EMAIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe otro comprador con ese email.");
            case DOCUMENTO_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe otro comprador con ese documento.");
            case TELEFONO_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe otro comprador con ese teléfono.");
            case CUITCUIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe otro comprador con ese CUIT/CUIL.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió otro error al guardar el comprador.");
        };
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un comprador por ID")
    @ApiResponse(responseCode = "200", description = "Comprador eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Comprador no encontrado")
    public ResponseEntity<String> eliminarComprador(@PathVariable("id") long id) {
        boolean eliminado = compradorService.eliminarComprador(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar el comprador con ID " + id);
        }

        return ResponseEntity.ok("Comprador eliminado correctamente.");
    }
}
