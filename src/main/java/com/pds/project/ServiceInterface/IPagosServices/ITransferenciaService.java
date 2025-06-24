package com.pds.project.ServiceInterface.IPagosServices;

import com.pds.project.Models.MetodosPago.Transferencia;
import com.pds.project.Implementation.PagosServices.TransferenciaService.ResultadoTransferencia;

public interface ITransferenciaService {
    ResultadoTransferencia guardarTransferencia(Transferencia transferencia);
}
