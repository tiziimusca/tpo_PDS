package com.pds.project.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long numeroPedido;

    @Column(name = "Fecha de creacion", length = 10)
    protected String fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "comprador_id")
    protected Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    protected Vehiculo vehiculo;

    @Column(name = "Configuracion adicional")
    protected String configuracionAdicional;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id")
    protected MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    protected Vendedor vendedor;

    @Column(name = "Etapa", length = 20)
    protected String etapa; // ventas, cobranzas, impuestos, embarque, logistica y entrega
}

