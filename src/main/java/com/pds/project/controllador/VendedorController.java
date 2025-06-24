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

import com.pds.project.Implementation.VendedorServiceImpl.ResultadoVendedor;
import com.pds.project.Models.Vendedor;
import com.pds.project.ServiceInterface.IVendedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/admin/vendedores") // Define un prefijo común para todas las rutas
public class VendedorController {

    @Autowired
    private IVendedorService vendedorService;

    @GetMapping
    @Operation(summary = "Obtener la lista de vendedores")
    @ApiResponse(responseCode = "200", description = "Lista de vendedores obtenida correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron vendedores")
    public ResponseEntity<List<Vendedor>> listarVendedores() {
        List<Vendedor> vendedores = vendedorService.getVendedores();
        return ResponseEntity.ok(vendedores);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un nuevo vendedor")
    @ApiResponse(responseCode = "200", description = "Vendedor guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el vendedor")
    public ResponseEntity<String> guardarVendedor(@RequestBody Vendedor vendedor) {
        ResultadoVendedor resultado = vendedorService.guardarVendedor(vendedor);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Comprador guardado con éxito.");
            case EMAIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe un vendedor con ese email.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió un error al guardar el comprador.");
        };
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar un vendedor existente")
    @ApiResponse(responseCode = "200", description = "Vendedor editado correctamente")
    @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")
    public ResponseEntity<String> editarVendedor(@PathVariable("id") long id,
            @RequestBody Vendedor datosActualizados) {
        ResultadoVendedor resultado = vendedorService.actualizarVendedor(id, datosActualizados);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Vendedor editado con éxito.");
            case EMAIL_DUPLICADO -> ResponseEntity.badRequest().body("Ya existe otro vendedor con ese email.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió un error al editar el vendedor.");
        };
    }

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener un vendedor por ID")
    @ApiResponse(responseCode = "200", description = "Vendedor encontrado")
    @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")
    public ResponseEntity<Vendedor> obtenerVendedor(@PathVariable("id") long id) {
        Vendedor vendedor = vendedorService.getVendedorById(id);

        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(vendedor);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un vendedor por ID")
    @ApiResponse(responseCode = "200", description = "Vendedor eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")
    public ResponseEntity<String> eliminarVendedor(@PathVariable("id") long id) {
        boolean eliminado = vendedorService.eliminarVendedor(id);

        if (eliminado) {
            return ResponseEntity.ok("Vendedor eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se pudo eliminar el vendedpr con ID " + id);
        }
    }
}