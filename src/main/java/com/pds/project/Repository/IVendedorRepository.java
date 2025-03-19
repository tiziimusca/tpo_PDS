package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Vendedor;

@Repository
public interface IVendedorRepository extends JpaRepository<Vendedor, Long> {

}
