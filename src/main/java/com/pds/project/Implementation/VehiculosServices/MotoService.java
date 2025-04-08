package com.pds.project.Implementation.VehiculosServices;

import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Moto;
import com.pds.project.ServiceInterface.IVehiculosServices.IMotoService;

@Service
public class MotoService implements IMotoService{
    @Override
    public Moto crearMoto() {
        return new Moto();
    }
}