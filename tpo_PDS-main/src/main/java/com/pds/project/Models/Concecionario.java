package com.pds.project.Models;
import java.util.ArrayList;

public class Concecionario {
   protected String nombre;
   protected Integer cuit; 
   protected ArrayList<Vehiculo> vehiculos;
   protected ArrayList<Vendedor> vendedores;
   protected ArrayList<Comprador> compradores;
   protected ArrayList<Pedido> pedidos;

   public Concecionario(String nombre, Integer cuit) {
      this.nombre = nombre;
      this.cuit = cuit;
      vehiculos = new ArrayList<Vehiculo>();
      vendedores = new ArrayList<Vendedor>();
      pedidos = new ArrayList<Pedido>();
      compradores = new ArrayList<Comprador>();
   }
}
