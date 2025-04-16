package com.pds.project.Models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;

    @Column(name = "etapa", length = 100, nullable = false)
    private String etapa;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "pedidoId", nullable = false)
    private Long pedidoId;

}
