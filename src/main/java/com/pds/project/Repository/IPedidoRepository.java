package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

}
