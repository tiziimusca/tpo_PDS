package com.pds.project.ServiceInterface.IVehiculosServices;

import com.pds.project.Implementation.VehiculosServices.CamionetaService.ResultadoCamioneta;
import com.pds.project.Models.vehiculos.Camioneta;

public interface ICamionetaService {
    ResultadoCamioneta guardarCamioneta(Camioneta camioneta);
}
