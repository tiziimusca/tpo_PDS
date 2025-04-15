package com.pds.project.Implementation.VehiculosServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pds.project.Models.Vehiculo;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVehiculosServices.IVehiculoService;

@Service
public class VehiculoServiceImpl  implements IVehiculoService{
    @Autowired
    private  IVehiculoRepository repoVehiculo;

    @Override
    public List<Vehiculo> getVehiculos() {
        return repoVehiculo.findAll();
    }

    public enum ResultadoVehiculo {
        OK,
        ERROR_CREACION_VEHICULO,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoVehiculo guardarVehiculo(Vehiculo vehiculo) {
        try {
            repoVehiculo.save(vehiculo);
            return ResultadoVehiculo.OK; 
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoVehiculo.ERROR_CREACION_VEHICULO;  
        }
    }

    @Override
    public Vehiculo getVehiculoById(Long id) {
        return repoVehiculo.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarVehiculo(Long id) {
            if(repoVehiculo.existsById(id)){
            repoVehiculo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ResultadoVehiculo actualizarVehiculo(long id, Vehiculo nuevosDatos) {
        Vehiculo vehiculoExistente = repoVehiculo.findById(id).orElse(null);
        try {
            vehiculoExistente.setMarca(nuevosDatos.getMarca());
            vehiculoExistente.setModelo(nuevosDatos.getModelo());
            vehiculoExistente.setColor(nuevosDatos.getColor());
            vehiculoExistente.setPrecio(nuevosDatos.getPrecio());
            vehiculoExistente.setNumeroChasis(nuevosDatos.getNumeroChasis());
            vehiculoExistente.setNumeroMotor(nuevosDatos.getNumeroMotor());
            vehiculoExistente.setEstado(nuevosDatos.getEstado());
            repoVehiculo.save(vehiculoExistente);
            return ResultadoVehiculo.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoVehiculo.ERROR_DESCONOCIDO;
        }
    }
}
