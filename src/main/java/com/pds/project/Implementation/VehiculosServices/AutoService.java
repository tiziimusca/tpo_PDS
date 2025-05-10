package com.pds.project.Implementation.VehiculosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Auto;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVehiculosServices.IAutoService;

@Service
public class AutoService  implements IAutoService{

    @Autowired
    private IVehiculoRepository repo; // Asegúrate de tener un repositorio para Moto

    public enum ResultadoAuto {
        OK,
        ERROR_DESCONOCIDO,
    }    
    
    @Override
    public ResultadoAuto guardarAuto(Auto auto) {
       try {
            repo.save(auto);
            return ResultadoAuto.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoAuto.ERROR_DESCONOCIDO;
        }
    }
    
}
