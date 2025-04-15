package com.pds.project.ServiceInterface.IVehiculosServices;

import java.util.List;

import com.pds.project.Implementation.VehiculosServices.VehiculoServiceImpl.ResultadoVehiculo;
import com.pds.project.Models.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> getVehiculos();
    ResultadoVehiculo guardarVehiculo(Vehiculo vehiculo);
    Vehiculo getVehiculoById(Long id);
    boolean eliminarVehiculo(Long id);
    ResultadoVehiculo actualizarVehiculo(long id, Vehiculo nuevosDatos);

}
