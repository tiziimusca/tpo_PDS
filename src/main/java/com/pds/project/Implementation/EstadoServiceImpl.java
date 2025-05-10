package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.pds.project.Events.EstadoPedidoChangedEvent;
import com.pds.project.Models.Estado;
import com.pds.project.Repository.IEstadoRepository;
import com.pds.project.ServiceInterface.IEstadoService;

@Service
public class EstadoServiceImpl implements IEstadoService {

    @Autowired
    private IEstadoRepository repoEstado;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<Estado> getEstados() {
        return repoEstado.findAll();
    }

    public enum ResultadoEstado {
        OK,
        PEDIDO_ETAPA_DUPLICADO,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoEstado guardarEstado(Estado estado) {

        if (repoEstado.existsByEtapaAndPedidoId(estado.getEtapa(), estado.getPedidoId())) {
            return ResultadoEstado.PEDIDO_ETAPA_DUPLICADO;
        }

        EstadoPedidoChangedEvent event = new EstadoPedidoChangedEvent(this, estado.getPedidoId(), estado.getEtapa(),
                estado.getFecha());
        eventPublisher.publishEvent(event);

        try {
            repoEstado.save(estado);
            return ResultadoEstado.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoEstado.ERROR_DESCONOCIDO;
        }
    }

    @Override
    public Estado getEstadoById(Long id) {
        return repoEstado.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarEstado(Long id) {
        if (repoEstado.existsById(id)) {
            repoEstado.deleteById(id);
            return true;
        }
        return false; // Retorna false si el estado no existía
    }

    @Override
    public ResultadoEstado actualizarEstado(long id, Estado nuevosDatos) {
        Estado estadoExistente = repoEstado.findById(id).orElse(null);

        try {
            estadoExistente.setEtapa(nuevosDatos.getEtapa());
            estadoExistente.setFecha(nuevosDatos.getFecha());
            estadoExistente.setPedidoId(nuevosDatos.getPedidoId());
            repoEstado.save(estadoExistente);

            EstadoPedidoChangedEvent event = new EstadoPedidoChangedEvent(this, nuevosDatos.getPedidoId(),
                    nuevosDatos.getEtapa(),
                    nuevosDatos.getFecha());
            eventPublisher.publishEvent(event);

            return ResultadoEstado.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoEstado.ERROR_DESCONOCIDO;
        }

    }
}
