package com.pds.project.ServiceInterface.IVehiculosServices;

import com.pds.project.Implementation.VehiculosServices.AutoService.ResultadoAuto;
import com.pds.project.Models.vehiculos.Auto;

public interface IAutoService  {
    ResultadoAuto guardarAuto(Auto auto);
}
