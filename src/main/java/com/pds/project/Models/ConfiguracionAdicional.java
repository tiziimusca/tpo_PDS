package com.pds.project.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "configuraciones_adicionales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConfig;

    @Column(name = "pedidoId", nullable = false)
    private Long pedidoId;

    @Column(name = "configuracion_adicional", length = 255, nullable = false)
    private String configuracionAdicional;
}
