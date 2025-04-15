package com.pds.project.controllador;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pds.project.Implementation.VehiculosServices.AutoService.ResultadoAuto;
import com.pds.project.Implementation.VehiculosServices.CamionService.ResultadoCamion;
import com.pds.project.Implementation.VehiculosServices.CamionetaService.ResultadoCamioneta;
import com.pds.project.Implementation.VehiculosServices.MotoService.ResultadoMoto;
import com.pds.project.Implementation.VehiculosServices.VehiculoServiceImpl.ResultadoVehiculo;
import com.pds.project.Models.Vehiculo;
import com.pds.project.Models.vehiculos.Auto;
import com.pds.project.Models.vehiculos.Camion;
import com.pds.project.Models.vehiculos.Camioneta;
import com.pds.project.Models.vehiculos.Moto;
import com.pds.project.ServiceInterface.IVehiculosServices.IAutoService;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionService;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionetaService;
import com.pds.project.ServiceInterface.IVehiculosServices.IMotoService;
import com.pds.project.ServiceInterface.IVehiculosServices.IVehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/vehiculos") // Define un prefijo común para todas las rutas
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;
    @Autowired
    private IMotoService motoService;
    @Autowired
    private IAutoService autoService;
    @Autowired
    private ICamionetaService camionetaService;
    @Autowired
    private ICamionService camionService;


    @GetMapping
    @Operation(summary = "Obtener la lista de vehiculos")
    @ApiResponse(responseCode = "200", description = "Lista de vehiculos obtenida correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron vehiculos")
    public ResponseEntity listarVehiculos (){
        List<Vehiculo> vehiculos = vehiculoService.getVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

 
    @PostMapping("/guardar/moto")
    @Operation(summary = "Guardar un vehiculo de tipo moto")
    @ApiResponse(responseCode = "200", description = "Moto guardada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar la moto")
    public ResponseEntity<String> guardarVehiculoMoto(Moto moto){
        ResultadoMoto resultado = motoService.guardarMoto(moto);
        
        if (resultado==ResultadoMoto.OK) {
            return ResponseEntity.ok("Moto guardada correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar la moto");
        }
    }

    @PostMapping("/guardar/auto")
    @Operation(summary = "Guardar un vehiculo de tipo auto")
    @ApiResponse(responseCode = "200", description = "Auto guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el auto")
    public ResponseEntity<String> guardarNuevoVehiculoAuto(Auto auto){
        ResultadoAuto resultado = autoService.guardarAuto(auto);
        if (resultado==ResultadoAuto.OK) {
            return ResponseEntity.ok("Auto guardado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar el auto");
            
        }
    }

    @PostMapping("/guardar/camioneta")
    @Operation(summary = "Guardar un vehiculo de tipo camioneta")
    @ApiResponse(responseCode = "200", description = "Camioneta guardada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar la camioneta")
    public ResponseEntity<String> guardarNuevoVehiculoCamioneta(Camioneta camioneta){
        ResultadoCamioneta resultado = camionetaService.guardarCamioneta(camioneta);
        if (resultado==ResultadoCamioneta.OK) {
            return ResponseEntity.ok("Camioneta guardada correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar la camioneta");
        }
    }

    @PostMapping("/guardar/camion")
    @Operation(summary = "Guardar un nuevo vehiculo de tipo camion")
    @ApiResponse(responseCode = "200", description = "Camion guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el camion")
    public ResponseEntity<String> guardarNuevoVehiculoCamion(Camion Camion){
        ResultadoCamion resultado = camionService.guardarCamion(Camion);
        if (resultado==ResultadoCamion.OK) {
            return ResponseEntity.ok("Camion guardado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar el camion");
        }
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Editar un vehiculo por ID")
    @ApiResponse(responseCode = "200", description = "Vehiculo editado correctamente")
    @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    public ResponseEntity<String> editarVehiculo(@PathVariable("id") Long id, @RequestBody Vehiculo vehiculo) { 
       ResultadoVehiculo resultado= vehiculoService.actualizarVehiculo(id, vehiculo);
        if (resultado==ResultadoVehiculo.OK) {
            return ResponseEntity.ok("Vehiculo editado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al editar el vehiculo");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un vehiculo por ID")
    @ApiResponse(responseCode = "200", description = "Vehiculo eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    public ResponseEntity<String> eliminarVehicuolo(@PathVariable("id") long id) {
        boolean eliminado = vehiculoService.eliminarVehiculo(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar el vehiculo con ID " + id);
        }

        return ResponseEntity.ok("Vehiculo eliminado correctamente.");
    }
}
