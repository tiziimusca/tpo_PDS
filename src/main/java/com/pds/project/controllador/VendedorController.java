package com.pds.project.controllador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pds.project.Models.Vendedor;
import com.pds.project.ServiceInterface.IVendedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/vendedores") // Define un prefijo común para todas las rutas
public class VendedorController {

    @Autowired
    private IVendedorService vendedorService;

    @GetMapping
    @Operation(summary = "Obtener la lista de vendedores")
    @ApiResponse(responseCode = "200", description = "Lista de vendedores obtenida correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron vendedores")
    public String listarVendedores(Model model) {
        model.addAttribute("vendedores", vendedorService.getVendedores());
        return "vendedores";
    }

    @GetMapping("/nuevo")
    @Operation(summary = "Crear un nuevo vendedor")
    @ApiResponse(responseCode = "200", description = "Vendedor creado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al crear el vendedor")
    public String nuevoVendedor(Model model) {
        model.addAttribute("vendedor", new Vendedor());
        return "nuevoVendedor";
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un nuevo vendedor")
    @ApiResponse(responseCode = "200", description = "Vendedor guardado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar el vendedor")
    public String guardarVendedor(@ModelAttribute Vendedor vendedor, RedirectAttributes attributes) {
        boolean result = vendedorService.guardarVendedor(vendedor);
        if (!result) {
            attributes.addFlashAttribute("error", "No se pudieron guardar los datos.");
            return "redirect:/vendedores/nuevo";
        }
        attributes.addFlashAttribute("success", "Los datos se guardaron correctamente.");
        return "redirect:/vendedores";
    }

    @GetMapping("/editar/{id}")
    @Operation(summary = "Editar un vendedor por ID")
    @ApiResponse(responseCode = "200", description = "Vendedor editado correctamente")
    @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")
    public String editarVendedor(@PathVariable("id") long id, Model model, RedirectAttributes attributes) {
        Vendedor vendedor = vendedorService.getVendedorById(id);
        if (vendedor == null) {
            attributes.addFlashAttribute("error", "No se encontró el vendedor con ID " + id);
            return "redirect:/vendedores";
        }
        model.addAttribute("vendedor", vendedor);
        return "nuevoVendedor";
    }

    @GetMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un vendedor por ID")
    @ApiResponse(responseCode = "200", description = "Vendedor eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Vendedor no encontrado")   
    public String eliminarVendedor(@PathVariable("id") long id, RedirectAttributes attributes) {
        boolean result = vendedorService.eliminarVendedor(id);
        if (!result) {
            attributes.addFlashAttribute("error", "No se pudo eliminar el vendedor con ID " + id);
        } else {
            attributes.addFlashAttribute("success", "Vendedor eliminado correctamente.");
        }
        return "redirect:/vendedores";
    }
}
