package com.pds.project.Implementation.PagosServices;

import com.pds.project.Models.MetodosPago.Transferencia;
import com.pds.project.Repository.IPagoRepository;
import com.pds.project.ServiceInterface.IPagosServices.ITransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService implements ITransferenciaService {

    @Autowired
    private IPagoRepository repo;

    public enum ResultadoTransferencia {
        OK,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoTransferencia guardarTransferencia(Transferencia transferencia) {
        try {
            repo.save(transferencia);
            return ResultadoTransferencia.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoTransferencia.ERROR_DESCONOCIDO;
        }
    }
}
