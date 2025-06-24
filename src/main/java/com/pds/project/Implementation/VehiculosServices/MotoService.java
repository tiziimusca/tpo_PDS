package com.pds.project.Implementation.VehiculosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Moto;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVehiculosServices.IMotoService;

@Service
public class MotoService implements IMotoService {

    @Autowired
    private IVehiculoRepository repo;

    public enum ResultadoMoto {
        OK,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoMoto guardarMoto(Moto moto) {
        try {
            repo.save(moto);
            return ResultadoMoto.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoMoto.ERROR_DESCONOCIDO;
        }
    }

}