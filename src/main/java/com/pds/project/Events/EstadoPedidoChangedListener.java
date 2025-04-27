package com.pds.project.Events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EstadoPedidoChangedListener {

    @EventListener
    public void manejarCambioEstado(EstadoPedidoChangedEvent evento) {
        System.out.println(
                "🔔 Pedido ID " + evento.getPedidoId() + "ha pasado a la etapa " + evento.getEtapa() +
                        " en la fecha " + evento.getFecha() + ".");

    }
}
