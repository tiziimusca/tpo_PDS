package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pds.project.Models.Vehiculo;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVehiculoService;

public class VehiculoServiceImpl  implements IVehiculoService{
    @Autowired
    private  IVehiculoRepository repoVehiculo;

    @Override
    public List<Vehiculo> getVehiculos() {
        return repoVehiculo.findAll();
    }

    @Override
    public boolean guardarVehiculo(Vehiculo vehiculo) {
        try {
            repoVehiculo.save(vehiculo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Vehiculo getVehiculoById(Long id) {
        return repoVehiculo.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarVehiculo(Long id) {
        try {
            repoVehiculo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
