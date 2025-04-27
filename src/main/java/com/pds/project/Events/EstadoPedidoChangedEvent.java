package com.pds.project.Events;

import lombok.Getter;

import java.time.LocalDate;

import org.springframework.context.ApplicationEvent;

@Getter
public class EstadoPedidoChangedEvent extends ApplicationEvent {

    private final long pedidoId;
    private final String etapa;
    private final LocalDate fecha;

    public EstadoPedidoChangedEvent(Object source, long pedidoId, String etapa, LocalDate fecha) {
        super(source);
        this.pedidoId = pedidoId;
        this.etapa = etapa;
        this.fecha = fecha;
    }
}
