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

import com.pds.project.Implementation.AdministradorServiceImpl.ResultadoPago;
import com.pds.project.Models.Pago;
import com.pds.project.ServiceInterface.IPagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/pagos") // Define un prefijo común para todas las rutas
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    @GetMapping
    @Operation(summary = "Obtener la lista de pagos")
    @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    public ResponseEntity<List<Pago>> listarPagos() {
        List<Pago> pagos = pagoService.getPagos();
        return ResponseEntity.ok(pagos);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un pago")
    @ApiResponse(responseCode = "200", description = "Pago guardado con éxito.")
    @ApiResponse(responseCode = "400", description = "No se pudieron guardar los datos del pago.")
    public ResponseEntity<String> guardarPago(@RequestBody Pago pago) {
        ResultadoPago resultado = pagoService.guardarPago(pago);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Pago guardado con éxito.");
            case PEDIDOID_DUPLICADO ->
                ResponseEntity.badRequest().body("Ya existe un pago con ese ID de pedido.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió un error al guardar el pago.");
        };
    }

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener un pago por ID")
    @ApiResponse(responseCode = "200", description = "Pago encontrado")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    public ResponseEntity<Pago> obtenerPago(@PathVariable("id") long id) {
        Pago pago = pagoService.getPagoById(id);

        if (pago == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pago);
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar un pago existente")
    @ApiResponse(responseCode = "200", description = "Pago actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    public ResponseEntity<String> editarPago(@PathVariable("id") long id,
            @RequestBody Pago datosActualizados) {
        ResultadoPago resultado = pagoService.actualizarPago(id, datosActualizados);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Pago guardado con éxito.");
            case ERROR_DESCONOCIDO ->
                ResponseEntity.badRequest().body("Ocurrió otro error al guardar el pago.");
        };
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un pago por ID")
    @ApiResponse(responseCode = "200", description = "Pago eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    public ResponseEntity<String> eliminarPago(@PathVariable("id") long id) {
        boolean eliminado = pagoService.eliminarPago(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar el pago con ID " + id);
        }

        return ResponseEntity.ok("Pago eliminado correctamente.");
    }
}
