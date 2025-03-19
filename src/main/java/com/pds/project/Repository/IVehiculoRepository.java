package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long>{

}
