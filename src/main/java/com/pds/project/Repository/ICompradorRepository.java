package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Comprador;

@Repository
public interface ICompradorRepository extends JpaRepository<Comprador, Long> {

    boolean existsByEmail(String email);

    boolean existsByDocumento(String documento);

    boolean existsByTelefono(String telefono);

    boolean existsByCuitCuil(String cuitCuil);
}
