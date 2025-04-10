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

}
