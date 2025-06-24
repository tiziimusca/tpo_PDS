package com.pds.project.ServiceInterface.IPagosServices;

import com.pds.project.Models.MetodosPago.Tarjeta;
import com.pds.project.Implementation.PagosServices.TarjetaService.ResultadoTarjeta;

public interface ITarjetaService {
    ResultadoTarjeta guardarTarjeta(Tarjeta tarjeta);
}
