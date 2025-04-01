package com.pds.project.ServiceInterface;

import java.util.List;
import com.pds.project.Models.Vendedor;

public interface IVendedorService {

    /**
     * Obtiene la lista de todos los vendedores registrados.
     * 
     * @return Lista de vendedores.
     */
    List<Vendedor> getVendedores();

    /**
     * Guarda o actualiza un vendedor en la base de datos.
     * 
     * @param vendedor Objeto vendedor a guardar.
     * @return true si la operación fue exitosa, false si hubo un error.
     */
    boolean guardarVendedor(Vendedor vendedor);

    /**
     * Busca un vendedor por su ID.
     * 
     * @param id Identificador único del vendedor.
     * @return Vendedor.
     */
    Vendedor getVendedorById(Long id);

    /**
     * Elimina un vendedor por su ID.
     * 
     * @param id Identificador del vendedor a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró el
     *         vendedor.
     */
    boolean eliminarVendedor(Long id);
}
