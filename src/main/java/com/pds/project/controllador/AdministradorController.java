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

import com.pds.project.Implementation.AdministradorServiceImpl.ResultadoAdministrador;
import com.pds.project.Models.Administrador;
import com.pds.project.ServiceInterface.IAdministradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/admin/administradores") // Define un prefijo común para todas las rutas
public class AdministradorController {

    @Autowired
    private IAdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Obtener la lista de administradores")
    @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida correctamente")
    public ResponseEntity<List<Administrador>> listarAdministradores() {
        List<Administrador> administradores = administradorService.getAdministradores();
        return ResponseEntity.ok(administradores);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un administrador")
    @ApiResponse(responseCode = "200", description = "Administrador guardado con éxito.")
    @ApiResponse(responseCode = "400", description = "No se pudieron guardar los datos del administrador.")
    public ResponseEntity<String> guardarAdministrador(@RequestBody Administrador administrador) {
        ResultadoAdministrador resultado = administradorService.guardarAdministrador(administrador);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Administrador guardado con éxito.");
            case EMAIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe un administrador con ese email.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió un error al guardar el administrador.");
        };
    }

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener un administrador por ID")
    @ApiResponse(responseCode = "200", description = "Administrador encontrado")
    @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    public ResponseEntity<Administrador> obtenerAdministrador(@PathVariable("id") long id) {
        Administrador administrador = administradorService.getAdministradorById(id);

        if (administrador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(administrador);
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar un administrador existente")
    @ApiResponse(responseCode = "200", description = "Administrador actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    public ResponseEntity<String> editarAdministrador(@PathVariable("id") long id,
            @RequestBody Administrador datosActualizados) {
        ResultadoAdministrador resultado = administradorService.actualizarAdministrador(id, datosActualizados);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Administrador guardado con éxito.");
            case EMAIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe otro administrador con ese email.");
            case ERROR_DESCONOCIDO ->
                ResponseEntity.badRequest().body("Ocurrió otro error al guardar el administrador.");
        };
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un administrador por ID")
    @ApiResponse(responseCode = "200", description = "Administrador eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    public ResponseEntity<String> eliminarAdministrador(@PathVariable("id") long id) {
        boolean eliminado = administradorService.eliminarAdministrador(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar el administrador con ID " + id);
        }

        return ResponseEntity.ok("Administrador eliminado correctamente.");
    }
}
