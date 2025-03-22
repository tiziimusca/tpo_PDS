package com.pds.project.controllador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pds.project.Models.Comprador;
import com.pds.project.ServiceInterface.ICompradorService;


@Controller
public class CompradorController {

    @Autowired
    private ICompradorService compradorService;

    @GetMapping("/Compradores")
    public String Inicio (Model model){
        model.addAttribute("Compradores", compradorService.getCompradores());
        return "Compradores";
    }

    @GetMapping("/compradores/nuevo")
    public String nuevoVendedor(Model model){
        model.addAttribute("Comprador", new Comprador());
        return "nuevoComprador";
    }

    @PostMapping("/Compradores/guardar")
    public String guardarVendedor(@ModelAttribute Comprador comprador, Model model, RedirectAttributes attributes){
        boolean result = compradorService.guardarComprador(comprador);
        if(!result){
            model.addAttribute("error", "No se pudieron guardar los datos");
            return "/Compradores/nuevo";
        }
        attributes.addFlashAttribute("success", "Los datos se gurdaron correctamente");
        return "redirect:/Compradores";
    }

    @GetMapping("/Compradores/editar/{id}")
    public String editarComprador(@PathVariable("id") long id, Model model, RedirectAttributes attributes){
        Comprador comprador = compradorService.getCompradorById(id);
        if(comprador != null){
            model.addAttribute("Comprador", comprador);
            return "/Compradores/nuevo";
        }
        attributes.addFlashAttribute("error", "No se encontro el comprador con ID "+ id);
        return "redirect:/Compradores";   
    }

    @GetMapping("/Compradores/eliminar/{id}")
    public String eliminarVendedor(@PathVariable("id") long id, RedirectAttributes attributes){
        boolean result = compradorService.eliminarComprador(id);
        if(!result ){
            attributes.addFlashAttribute("error", "No se pudo eliminar el comprador con ID "+ id);
        }else{
            attributes.addFlashAttribute("success", "comprador eliminado correctamente");
        }
        return "redirect:/Compradores";
    }
}