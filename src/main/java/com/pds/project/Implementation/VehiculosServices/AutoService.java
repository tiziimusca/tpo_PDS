package com.pds.project.Implementation.VehiculosServices;

import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Auto;
import com.pds.project.ServiceInterface.IVehiculosServices.IAutoService;

@Service
public class AutoService  implements IAutoService{
    @Override
    public Auto crearAuto() {
        return new Auto();
    }
    
}
