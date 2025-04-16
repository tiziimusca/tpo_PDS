package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Administrador;

@Repository
public interface IAdministradorRepository extends JpaRepository<Administrador, Long> {

    boolean existsByEmail(String email);

}
