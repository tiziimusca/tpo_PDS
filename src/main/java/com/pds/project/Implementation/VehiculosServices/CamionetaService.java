package com.pds.project.Implementation.VehiculosServices;

import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Camioneta;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionetaService;

@Service
public class CamionetaService implements ICamionetaService {
    @Override
    public Camioneta crearCamioneta() {
        return new Camioneta();
    }
}
