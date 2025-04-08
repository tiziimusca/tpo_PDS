package com.pds.project.Implementation.VehiculosServices;

import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Camion;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionService;

@Service
public class CamionService  implements ICamionService {

    @Override
    public Camion crearCamion() {
        return new Camion();
    }
    
    
}
