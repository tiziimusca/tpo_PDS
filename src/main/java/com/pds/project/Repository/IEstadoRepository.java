package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import com.pds.project.Models.Estado;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Long> {

    boolean existsByEtapaAndPedidoId(String etapa, Long pedidoId);

    Optional<Estado> findTopByPedidoIdOrderByFechaDesc(Long numeroPedido);

    List<Estado> findByEtapa(String nombre);

}
