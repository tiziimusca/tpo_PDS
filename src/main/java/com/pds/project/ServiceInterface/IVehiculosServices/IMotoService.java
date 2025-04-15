package com.pds.project.ServiceInterface.IVehiculosServices;

import com.pds.project.Implementation.VehiculosServices.MotoService.ResultadoMoto;
import com.pds.project.Models.vehiculos.Moto;

public interface IMotoService{
    ResultadoMoto guardarMoto(Moto moto);
}