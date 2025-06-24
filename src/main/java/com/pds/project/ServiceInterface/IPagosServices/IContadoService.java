package com.pds.project.ServiceInterface.IPagosServices;

import com.pds.project.Models.MetodosPago.Contado;
import com.pds.project.Implementation.PagosServices.ContadoService.ResultadoContado;

public interface IContadoService {
    ResultadoContado guardarContado(Contado contado);
}
