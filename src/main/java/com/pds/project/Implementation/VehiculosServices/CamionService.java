package com.pds.project.Implementation.VehiculosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Camion;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVehiculosServices.ICamionService;

@Service
public class CamionService  implements ICamionService {

    @Autowired
    private IVehiculoRepository repo; // Asegúrate de tener un repositorio para Moto

    public enum ResultadoCamion {
        OK,
        ERROR_DESCONOCIDO,
    }   

    @Override
    public ResultadoCamion guardarCamion(Camion camion) {
       try {
            repo.save(camion);
            return ResultadoCamion.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoCamion.ERROR_DESCONOCIDO;
        }
    }
    
    
}
