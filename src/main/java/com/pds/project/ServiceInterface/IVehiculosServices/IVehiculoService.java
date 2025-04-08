package com.pds.project.ServiceInterface.IVehiculosServices;

import java.util.List;

import com.pds.project.Models.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> getVehiculos();
    boolean guardarVehiculo(Vehiculo vehiculo);
    Vehiculo getVehiculoById(Long id);
    boolean eliminarVehiculo(Long id);

}
