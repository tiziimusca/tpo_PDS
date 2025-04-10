package com.pds.project.controllador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pds.project.Models.Vehiculo;
import com.pds.project.ServiceInterface.IVehiculosServices.IAutoService;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionService;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionetaService;
import com.pds.project.ServiceInterface.IVehiculosServices.IMotoService;
import com.pds.project.ServiceInterface.IVehiculosServices.IVehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/vendedores") // Define un prefijo común para todas las rutas
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
    public String Inicio (Model model){
        model.addAttribute("Vehiculos", vehiculoService.getVehiculos());
        return "Vehiculos";
    }

    @GetMapping("/nuevo/moto")
    @Operation(summary = "Crear un nuevo vehiculo de tipo moto")
    @ApiResponse(responseCode = "200", description = "Moto creada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al crear la moto")
    public String nuevoVehiculoMoto(Model model){
        model.addAttribute("vehiculo", motoService.crearMoto());
        return "nuevoVehiculo";
    }
    @GetMapping("/nuevo/auto")
    @Operation(summary = "Crear un nuevo vehiculo de tipo auto")
    @ApiResponse(responseCode = "200", description = "Auto creado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al crear el auto")
    public String nuevoVehiculoAuto(Model model){
        model.addAttribute("vehiculo", autoService.crearAuto());
        return "nuevoVehiculo";
    }
    @GetMapping("/nuevo/camioneta")
    @Operation(summary = "Crear un nuevo vehiculo de tipo camioneta")
    @ApiResponse(responseCode = "200", description = "Camioneta creada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al crear la camioneta")
    public String nuevoVehiculoCamioneta(Model model){
        model.addAttribute("vehiculo", camionetaService.crearCamioneta());
        return "nuevoVehiculo";
    }
    @GetMapping("/nuevo/camion")
    @Operation(summary = "Crear un nuevo vehiculo de tipo camion")
    @ApiResponse(responseCode = "200", description = "Camion creado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al crear el camion")
    public String nuevoVehiculoCamion(Model model){
        model.addAttribute("vehiculo", camionService.crearCamion());
        return "nuevoVehiculo";
    }


    @PostMapping("/guardar")
    @Operation(summary = "Guardar un vehiculo")
    @ApiResponse(responseCode = "200", description = "Vehiculo guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el vehiculo")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo, Model model, RedirectAttributes attributes){
        boolean result = vehiculoService.guardarVehiculo(vehiculo);
        if(!result){
            model.addAttribute("error", "No se pudieron guardar los datos");
            return "/nuevo";
        }
        attributes.addFlashAttribute("success", "Los datos se gurdaron correctamente");
        return "redirect:/Vehiculos";
    }

    @GetMapping("/editar/{id}")
    @Operation(summary = "Editar un vehiculo por ID")
    @ApiResponse(responseCode = "200", description = "Vehiculo editado correctamente")
    @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    public String editarVehiculo(@PathVariable("id") Long id, Model model, RedirectAttributes attributes){ 
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
        if (vehiculo == null) {
            model.addAttribute("error", "Vehiculo no encontrado");
            return "redirect:/Vehiculos";
        }
        model.addAttribute("Vehiculo", vehiculo);
        return "editarVehiculo";
    }

    @GetMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un vehiculo por ID")
    @ApiResponse(responseCode = "200", description = "Vehiculo eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    public String eliminarVehiculo(@PathVariable("id") Long id, RedirectAttributes attributes){
        boolean result = vehiculoService.eliminarVehiculo(id);
        if (!result) {
            attributes.addFlashAttribute("error", "No se pudo eliminar el vehiculo con ID "+ id);
        }
        else {
            attributes.addFlashAttribute("success", "Vehiculo eliminado correctamente");
        }
        return "redirect:/Vehiculos";
    }
}
