package com.pds.project.controllador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pds.project.Implementation.PedidoServiceImpl.ResultadoPedido;
import com.pds.project.Models.Pedido;
import com.pds.project.Models.InformeFacade;
import com.pds.project.ServiceInterface.IPedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/pedidos") // Define un prefijo común para todas las rutas
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping("/generar-informe")
    public ResponseEntity<String> generarInforme() {
        List<Pedido> pedidos = pedidoService.getPedidos(); // asumido
        String ruta = "informe_pedidos.csv";

        InformeFacade.generarInformeCSV(pedidos, ruta);

        return ResponseEntity.ok("Informe generado exitosamente");
    }

    @GetMapping
    @Operation(summary = "Obtener la lista de pedidos")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.getPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un pedido")
    @ApiResponse(responseCode = "200", description = "Pedido guardado con éxito.")
    @ApiResponse(responseCode = "400", description = "No se pudieron guardar los datos del pedido.")
    public ResponseEntity<String> guardarPedido(@RequestBody Pedido pedido) {
        ResultadoPedido resultado = pedidoService.guardarPedido(pedido);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Pedido guardado con éxito.");
            case VEHICULOID_DUPLICADO ->
                ResponseEntity.badRequest().body("El vehículo ya está asociado a otro pedido.");
            case ERROR_DESCONOCIDO -> ResponseEntity.badRequest().body("Ocurrió un error al guardar el pedido.");
        };
    }

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener un pedido por ID")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable("id") long id) {
        Pedido pedido = pedidoService.getPedidoById(id);

        if (pedido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pedido);
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar un pedido existente")
    @ApiResponse(responseCode = "200", description = "Pedido actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<String> editarPedido(@PathVariable("id") long id,
            @RequestBody Pedido datosActualizados) {
        ResultadoPedido resultado = pedidoService.actualizarPedido(id, datosActualizados);

        return switch (resultado) {
            case OK -> ResponseEntity.ok("Pedido guardado con éxito.");
            case VEHICULOID_DUPLICADO ->
                ResponseEntity.badRequest().body("El vehículo ya está asociado a otro pedido.");
            case ERROR_DESCONOCIDO ->
                ResponseEntity.badRequest().body("Ocurrió otro error al guardar el pedido.");
        };
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un pedido por ID")
    @ApiResponse(responseCode = "200", description = "Pedido eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<String> eliminarPedido(@PathVariable("id") long id) {
        boolean eliminado = pedidoService.eliminarPedido(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar el pedido con ID " + id);
        }

        return ResponseEntity.ok("Pedido eliminado correctamente.");
    }
}