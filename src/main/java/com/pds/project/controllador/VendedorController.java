package com.pds.project.controllador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.pds.project.ServiceInterface.IVendedorService;

@Controller
public class VendedorController {

    @Autowired
    private IVendedorService repoVendedor;

    @GetMapping("/vendedores")
    public String Inicio (Model model){
        model.addAttribute("vendedores", repoVendedor.getVehiculos());
        return "VendedoresView";
    }
}
