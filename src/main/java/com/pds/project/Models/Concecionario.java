package com.pds.project.Models;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Concecionario {
   protected String nombre;
   protected Integer cuit; 
   protected ArrayList<Vehiculo> catalogo;
   protected ArrayList<Vendedor> vendedores;
   protected ArrayList<Comprador> compradores;
   protected ArrayList<Pedido> pedidos; 
}
