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

import com.pds.project.Implementation.PagosServices.ContadoService.ResultadoContado;
import com.pds.project.Implementation.PagosServices.PagoServiceImpl.ResultadoPago;
import com.pds.project.Implementation.PagosServices.TarjetaService.ResultadoTarjeta;
import com.pds.project.Implementation.PagosServices.TransferenciaService.ResultadoTransferencia;
import com.pds.project.Models.Pago;
import com.pds.project.Models.MetodosPago.Contado;
import com.pds.project.Models.MetodosPago.Tarjeta;
import com.pds.project.Models.MetodosPago.Transferencia;
import com.pds.project.ServiceInterface.IPagosServices.IPagoService;
import com.pds.project.ServiceInterface.IPagosServices.IContadoService;
import com.pds.project.ServiceInterface.IPagosServices.ITarjetaService;
import com.pds.project.ServiceInterface.IPagosServices.ITransferenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/admin/pagos") // Define un prefijo común para todas las rutas
public class PagoController {

    @Autowired
    private IPagoService pagoService;
    @Autowired
    private IContadoService contadoService;
    @Autowired
    private ITarjetaService tarjetaService;
    @Autowired
    private ITransferenciaService transferenciaService;

    @GetMapping
    @Operation(summary = "Obtener la lista de pagos")
    @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    public ResponseEntity<List<Pago>> listarPagos() {
        List<Pago> pagos = pagoService.getPagos();
        return ResponseEntity.ok(pagos);
    }

    @PostMapping("/guardar/contado")
    @Operation(summary = "Guardar un pago de tipo contado")
    @ApiResponse(responseCode = "200", description = "Contado guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el pago contado")
    public ResponseEntity<String> guardarNuevoPagoContado(Contado contado) {
        ResultadoContado resultado = contadoService.guardarContado(contado);

        if (resultado == ResultadoContado.OK) {
            return ResponseEntity.ok("Pago contado guardado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar el pago contado");
        }
    }

    @PostMapping("/guardar/tarjeta")
    @Operation(summary = "Guardar un pago de tipo tarjeta")
    @ApiResponse(responseCode = "200", description = "Tarjeta guardada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el pago tarjeta")
    public ResponseEntity<String> guardarNuevoPagoTarjeta(Tarjeta tarjeta) {
        ResultadoTarjeta resultado = tarjetaService.guardarTarjeta(tarjeta);
        if (resultado == ResultadoTarjeta.OK) {
            return ResponseEntity.ok("Tarjeta guardada correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar la tarjeta");
        }
    }

    @PostMapping("/guardar/transferencia")
    @Operation(summary = "Guardar un nuevo pago de tipo transferencia")
    @ApiResponse(responseCode = "200", description = "Transferencia guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el pago transferencia")
    public ResponseEntity<String> guardarNuevoPagoTransferencia(Transferencia transferencia) {
        ResultadoTransferencia resultado = transferenciaService.guardarTransferencia(transferencia);
        if (resultado == ResultadoTransferencia.OK) {
            return ResponseEntity.ok("Transferencia guardado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar el pago transferencia");
        }
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
            case PEDIDOID_DUPLICADO ->
                ResponseEntity.badRequest().body("Ya existe un pago con ese ID de pedido.");
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
