package com.pds.project.controllador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String listarCompradores(Model model) {
        model.addAttribute("compradores", compradorService.getCompradores());
        return "compradores";
    }

    @GetMapping("/nuevo")
    @Operation(summary = "Crear un nuevo comprador")
    @ApiResponse(responseCode = "200", description = "Comprador creado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al crear el comprador")
    public String nuevoComprador(Model model) {
        model.addAttribute("comprador", new Comprador());
        return "nuevoComprador";
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un comprador")
    @ApiResponse(responseCode = "200", description = "Comprador guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el comprador")
    public String guardarComprador(@ModelAttribute Comprador comprador, RedirectAttributes attributes) {
        boolean result = compradorService.guardarComprador(comprador);
        if (!result) {
            attributes.addFlashAttribute("error", "No se pudieron guardar los datos.");
            return "redirect:/compradores/nuevo";
        }
        attributes.addFlashAttribute("success", "Los datos se guardaron correctamente.");
        return "redirect:/compradores";
    }

    @GetMapping("/editar/{id}")
    @Operation(summary = "Editar un comprador existente")
    @ApiResponse(responseCode = "200", description = "Comprador editado correctamente")
    @ApiResponse(responseCode = "404", description = "Comprador no encontrado")
    public String editarComprador(@PathVariable("id") long id, Model model, RedirectAttributes attributes) {
        Comprador comprador = compradorService.getCompradorById(id);
        if (comprador == null) {
            attributes.addFlashAttribute("error", "No se encontró el comprador con ID " + id);
            return "redirect:/compradores";
        }
        model.addAttribute("comprador", comprador);
        return "nuevoComprador";
    }

    @GetMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un comprador por ID")
    @ApiResponse(responseCode = "200", description = "Comprador eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Comprador no encontrado")
    public String eliminarComprador(@PathVariable("id") long id, RedirectAttributes attributes) {
        boolean result = compradorService.eliminarComprador(id);
        if (!result) {
            attributes.addFlashAttribute("error", "No se pudo eliminar el comprador con ID " + id);
        } else {
            attributes.addFlashAttribute("success", "Comprador eliminado correctamente.");
        }
        return "redirect:/compradores";
    }
}
