package com.pds.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.project.Models.Comprador;

@Repository
public interface ICompradorRepository extends JpaRepository<Comprador, Long> {
    /*
     * // Buscar comprador por email (Spring genera la consulta automáticamente)
     * Optional<Comprador> findByEmail(String email);
     * 
     * // Buscar compradores por apellido
     * List<Comprador> findByApellido(String apellido);
     * 
     * // Buscar comprador por documento
     * Optional<Comprador> findByDocumento(Integer documento);
     * 
     * // Verificar si un email ya existe
     * boolean existsByEmail(String email);
     * 
     * 
     * Cómo usarlo en el servicio
     * Si quieres usar estos métodos en CompradorServiceImpl, lo harías así:
     * 
     * public Optional<Comprador> buscarPorEmail(String email) {
     * return repoComprador.findByEmail(email);}
     */
}
