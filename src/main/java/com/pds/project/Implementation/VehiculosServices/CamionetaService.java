package com.pds.project.Implementation.VehiculosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Camioneta;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionetaService;

@Service
public class CamionetaService implements ICamionetaService {

    @Autowired
    private IVehiculoRepository repo;

    public enum ResultadoCamioneta {
        OK,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoCamioneta guardarCamioneta(Camioneta camioneta) {
        try {
            repo.save(camioneta);
            return ResultadoCamioneta.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoCamioneta.ERROR_DESCONOCIDO;
        }
    }
}
