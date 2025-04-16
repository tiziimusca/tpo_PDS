package com.pds.project.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPedido;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "compradorId", nullable = false)
    private Long compradorId;

    @Column(name = "vehiculoId", nullable = false)
    private Long vehiculoId;

    @Column(name = "vendedorId", nullable = false)
    private Long vendedorId;

}
