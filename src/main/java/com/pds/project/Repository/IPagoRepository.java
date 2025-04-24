package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Pago;

@Repository
public interface IPagoRepository extends JpaRepository<Pago, Long> {

    boolean existsByPedidoId(Long pedidoId);
}
