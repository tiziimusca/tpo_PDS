package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Models.Pago;
import com.pds.project.Repository.IPagoRepository;
import com.pds.project.ServiceInterface.IPagoService;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private IPagoRepository repoPago;

    @Override
    public List<Pago> getPagos() {
        return repoPago.findAll();
    }

    public enum ResultadoPago {
        OK,
        PEDIDOID_DUPLICADO,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoPago guardarPago(Pago pago) {

        if (repoPago.existsByPedidoId(pago.getPedidoId())) {
            return ResultadoPago.PEDIDOID_DUPLICADO;
        }

        try {
            repoPago.save(pago);
            return ResultadoPago.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPago.ERROR_DESCONOCIDO;
        }
    }

    @Override
    public Pago getPagoById(Long id) {
        return repoPago.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarPago(Long id) {
        if (repoPago.existsById(id)) {
            repoPago.deleteById(id);
            return true;
        }
        return false; // Retorna false si el pago no existía
    }

    @Override
    public ResultadoPago actualizarPago(long id, Pago nuevosDatos) {
        Pago pagoExistente = repoPago.findById(id).orElse(null);

        try {
            pagoExistente.setMonto(nuevosDatos.getMonto());
            pagoExistente.setFechaPago(nuevosDatos.getFechaPago());
            pagoExistente.setPedidoId(nuevosDatos.getPedidoId());
            repoPago.save(pagoExistente);
            return ResultadoPago.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPago.ERROR_DESCONOCIDO;
        }

    }
}
