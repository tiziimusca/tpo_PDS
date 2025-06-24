package com.pds.project.Implementation.PagosServices;

import com.pds.project.Models.MetodosPago.Tarjeta;
import com.pds.project.Repository.IPagoRepository;
import com.pds.project.ServiceInterface.IPagosServices.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaService implements ITarjetaService {

    @Autowired
    private IPagoRepository repo;

    public enum ResultadoTarjeta {
        OK,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoTarjeta guardarTarjeta(Tarjeta tarjeta) {
        try {
            repo.save(tarjeta);
            return ResultadoTarjeta.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoTarjeta.ERROR_DESCONOCIDO;
        }
    }
}
