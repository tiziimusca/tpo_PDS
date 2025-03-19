package com.pds.project.Implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.Vehiculo;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVendedorService;

@Service
public class VendedorServiceImpl implements IVendedorService{

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> getVehiculos() {
        return vehiculoRepository.findAll();
    }
}
