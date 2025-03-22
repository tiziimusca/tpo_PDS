package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Models.Vehiculo;

public interface IVehiculoService {
    public List<Vehiculo> getVehiculos();
    public boolean guardarVehiculo(Vehiculo vehiculo);
    public Vehiculo getVehiculoById(Long id);
    public boolean eliminarVehiculo(Long id);

}
