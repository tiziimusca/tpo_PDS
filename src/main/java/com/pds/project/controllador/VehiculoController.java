package com.pds.project.controllador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pds.project.Models.Vehiculo;
import com.pds.project.ServiceInterface.IVehiculoService;

public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping("/Vehiculos")
    public String Inicio (Model model){
        model.addAttribute("Vehiculos", vehiculoService.getVehiculos());
        return "Vehiculos";
    }

    @GetMapping("/vehiculos/nuevo")
    public String nuevoVehiculo(Model model){
        model.addAttribute("Vehiculo", new Vehiculo());
        return "nuevoVehiculo";
    }

    @PostMapping("/Vehiculos/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo, Model model, RedirectAttributes attributes){
        boolean result = vehiculoService.guardarVehiculo(vehiculo);
        if(!result){
            model.addAttribute("error", "No se pudieron guardar los datos");
            return "/Vehiculos/nuevo";
        }
        attributes.addFlashAttribute("success", "Los datos se gurdaron correctamente");
        return "redirect:/Vehiculos";
    }

    @GetMapping("/Vehiculos/editar/{id}")
    public String editarVehiculo(@PathVariable("id") Long id, Model model, RedirectAttributes attributes){ 
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
        if (vehiculo == null) {
            model.addAttribute("error", "Vehiculo no encontrado");
            return "redirect:/Vehiculos";
        }
        model.addAttribute("Vehiculo", vehiculo);
        return "editarVehiculo";
    }

    @GetMapping("/Vehiculos/eliminar/{id}")
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
