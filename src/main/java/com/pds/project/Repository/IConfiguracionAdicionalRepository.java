package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.ConfiguracionAdicional;

@Repository
public interface IConfiguracionAdicionalRepository extends JpaRepository<ConfiguracionAdicional, Long> {

    boolean existsByPedidoId(Long pedidoId);
}
