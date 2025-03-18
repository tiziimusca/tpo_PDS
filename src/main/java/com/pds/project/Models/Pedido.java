package com.pds.project.Models;

public class Pedido {
    protected Integer numeroPedido;
    protected String fechaCreacion;
    protected Comprador comprador;
    protected Vehiculo vehiculo;
    protected String configuracionAdicional;
    protected MetodoPago metodoPago;
    protected Vendedor vendedor;
    protected Factura datosFactura;
    protected String etapa; //ventas, cobranzas, impuestos, embarque, logistica y entrega

    public Pedido(Integer numeroPedido, String fechaCreacion, Comprador comprador, Vehiculo vehiculo, String configuracionAdicional, MetodoPago metodoPago, Vendedor vendedor, Factura factura, String etapa) {
        this.numeroPedido = numeroPedido;
        this.fechaCreacion = fechaCreacion;
        this.comprador = comprador;
        this.vehiculo = vehiculo;
        this.configuracionAdicional = configuracionAdicional;
        this.metodoPago = metodoPago;
        this.vendedor = vendedor;
        this.datosFactura = factura;
        etapa = "ventas";
    }
}
