package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Concesionario;

@Repository
public interface IConcesionarioRepository extends JpaRepository<Concesionario, Long> {

}
