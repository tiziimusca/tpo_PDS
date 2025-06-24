package com.pds.project.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.project.Events.EstadoPedidoChangedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.pds.project.Models.Pedido;
import com.pds.project.Repository.IPedidoRepository;
import com.pds.project.ServiceInterface.IPedidoService;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IPedidoRepository repoPedido;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<Pedido> getPedidos() {
        return repoPedido.findAll();
    }

    public enum ResultadoPedido {
        OK,
        VEHICULOID_DUPLICADO,
        ERROR_DESCONOCIDO,
    }

    @Override
    public ResultadoPedido guardarPedido(Pedido pedido) {

        if (repoPedido.existsByVehiculoId(pedido.getVehiculoId())) {
            return ResultadoPedido.VEHICULOID_DUPLICADO;
        }
        try {
            repoPedido.save(pedido);

            EstadoPedidoChangedEvent event = new EstadoPedidoChangedEvent(this, pedido.getNumeroPedido(),
                    "VENTAS", pedido.getFechaCreacion());

            eventPublisher.publishEvent(event);

            return ResultadoPedido.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPedido.ERROR_DESCONOCIDO;
        }
    }

    @Override
    public Pedido getPedidoById(Long id) {
        return repoPedido.findById(id).orElse(null);
    }

    @Override
    public boolean eliminarPedido(Long id) {
        if (repoPedido.existsById(id)) {
            repoPedido.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ResultadoPedido actualizarPedido(long id, Pedido nuevosDatos) {
        Pedido pedidoExistente = repoPedido.findById(id).orElse(null);

        try {
            pedidoExistente.setCompradorId(nuevosDatos.getCompradorId());
            pedidoExistente.setFechaCreacion(nuevosDatos.getFechaCreacion());
            pedidoExistente.setVehiculoId(nuevosDatos.getVehiculoId());
            pedidoExistente.setVendedorId(nuevosDatos.getVendedorId());
            repoPedido.save(pedidoExistente);
            return ResultadoPedido.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPedido.ERROR_DESCONOCIDO;
        }

    }

    @Override
    public List<Pedido> getPedidosByCompradorId(long id) {
        List<Pedido> pedidos = repoPedido.findAll();
        List<Pedido> pedidosPorComprador = pedidos.stream()
                .filter(pedido -> pedido.getCompradorId() == id)
                .toList();
        return pedidosPorComprador;
    }
}
