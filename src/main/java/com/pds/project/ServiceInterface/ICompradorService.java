package com.pds.project.ServiceInterface;

import java.util.List;
import com.pds.project.Models.Comprador;

public interface ICompradorService {

    /**
     * Obtiene la lista de todos los compradores registrados.
     * 
     * @return Lista de compradores.
     */
    List<Comprador> getCompradores();

    /**
     * Guarda o actualiza un comprador en la base de datos.
     * 
     * @param comprador Objeto Comprador a guardar.
     * @return true si la operación fue exitosa, false si hubo un error.
     */
    boolean guardarComprador(Comprador comprador);

    /**
     * Busca un comprador por su ID.
     * 
     * @param id Identificador único del comprador.
     * @return Comprador.
     */
    Comprador getCompradorById(Long id);

    /**
     * Elimina un comprador por su ID.
     * 
     * @param id Identificador del comprador a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró el
     *         comprador.
     */
    boolean eliminarComprador(Long id);
}
