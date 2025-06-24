package com.pds.project.Implementation.PagosServices;

import com.pds.project.Models.MetodosPago.Contado;
import com.pds.project.Repository.IPagoRepository;
import com.pds.project.ServiceInterface.IPagosServices.IContadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContadoService implements IContadoService {

    @Autowired
    private IPagoRepository repo;

    public enum ResultadoContado {
        OK,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoContado guardarContado(Contado contado) {
        try {
            repo.save(contado);
            return ResultadoContado.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoContado.ERROR_DESCONOCIDO;
        }
    }
}
