package com.pds.project.controllador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pds.project.Models.Vendedor;
import com.pds.project.ServiceInterface.IVendedorService;

@Controller
public class VendedorController {

    @Autowired
    private IVendedorService vendedorService;

    @GetMapping("/vendedores")
    public String Inicio (Model model){
        model.addAttribute("vendedores", vendedorService.getVendedores());
        return "Vendedores";
    }

    @GetMapping("/vendedores/nuevo")
    public String nuevoVendedor(Model model){
        model.addAttribute("vendedor", new Vendedor());
        return "nuevoVendedor";
    }

    @PostMapping("/guardar")
    public String guardarVendedor(@ModelAttribute Vendedor vendedor, Model model, RedirectAttributes attributes){
        boolean result = vendedorService.guardarVendedor(vendedor);
        if(result != false){
            model.addAttribute("error", "No se pudieron guardar los datos");
            return "/vendedores/nuevo";
        }
        attributes.addFlashAttribute("success", "Los datos se gurdaron correctamente");
        return "redirect:/vendedores";
    }

    @GetMapping("/vendedores/editar/{id}")
    public String editarVendedor(@PathVariable("id") long id, Model model, RedirectAttributes attributes){
        Vendedor vendedor = vendedorService.getVendedorById(id);
        if(vendedor != null){
            model.addAttribute("vendedor", vendedor);
            return "/vendedores/nuevo";
        }
        attributes.addFlashAttribute("error", "No se encontro el vendedor con ID "+id);
        return "redirect:/vendedores";   
    }

    @GetMapping("/vendedores/eliminar/{id}")
    public String eliminarVendedor(@PathVariable("id") long id, RedirectAttributes attributes){
        boolean result = vendedorService.eliminarVendedor(id);
        if(result == false){
            attributes.addFlashAttribute("error", "No se pudo eliminar el vendedor con ID "+id);
        }else{
            attributes.addFlashAttribute("success", "Vendedor eliminado correctamente");
        }
        return "redirect:/vendedores";
    }
}
