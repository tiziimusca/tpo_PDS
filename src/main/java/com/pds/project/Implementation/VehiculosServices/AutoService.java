package com.pds.project.Implementation.VehiculosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.vehiculos.Auto;
import com.pds.project.Repository.IVehiculoRepository;
import com.pds.project.ServiceInterface.IVehiculosServices.IAutoService;
import java.util.Optional;

@Service
public class AutoService implements IAutoService {

    @Autowired
    private IVehiculoRepository repo;

    public enum ResultadoAuto {
        OK,
        ERROR_DESCONOCIDO,
        NO_EXISTE,
    }

    @Override
    public ResultadoAuto guardarAuto(Auto auto) {
        try {
            if (auto.getIdVehiculo() != null) {
                // Es un update. Buscamos el auto existente
                Optional<Auto> existente = repo.findById(auto.getIdVehiculo()).map(v -> (Auto) v);
                if (existente.isPresent()) {
                    Auto existenteAuto = existente.get();

                    // Actualizamos solo los campos necesarios
                    existenteAuto.setColor(auto.getColor());
                    existenteAuto.setEstado(auto.getEstado());
                    existenteAuto.setMarca(auto.getMarca());
                    existenteAuto.setModelo(auto.getModelo());
                    existenteAuto.setNumeroChasis(auto.getNumeroChasis());
                    existenteAuto.setNumeroMotor(auto.getNumeroMotor());
                    existenteAuto.setPrecio(auto.getPrecio());

                    repo.save(existenteAuto);
                } else {
                    return ResultadoAuto.NO_EXISTE;
                }
            } else {
                // Es un nuevo auto
                repo.save(auto);
            }
            return ResultadoAuto.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoAuto.ERROR_DESCONOCIDO;
        }
    }
}
