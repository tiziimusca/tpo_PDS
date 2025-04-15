package com.pds.project.ServiceInterface.IVehiculosServices;

import com.pds.project.Implementation.VehiculosServices.CamionService.ResultadoCamion;
import com.pds.project.Models.vehiculos.Camion;

public interface ICamionService  {
    ResultadoCamion guardarCamion(Camion camion);
}
