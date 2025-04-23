package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Implementation.EstadoServiceImpl.ResultadoEstado;
import com.pds.project.Models.Estado;

public interface IEstadoService {

    /**
     * Obtiene la lista de todos los estados registrados.
     * 
     * @return Lista de estados.
     */
    List<Estado> getEstados();

    /**
     * Guarda o actualiza un estado en la base de datos.
     * 
     * @param estado Objeto Estado a guardar.
     * @return true si la operación fue exitosa, false si hubo un error.
     */
    ResultadoEstado guardarEstado(Estado estado);

    /**
     * Busca un estado por su ID.
     * 
     * @param id Identificador único del estado.
     * @return Estado.
     */
    Estado getEstadoById(Long id);

    /**
     * Elimina un estado por su ID.
     * 
     * @param id Identificador del estado a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró el
     *         estado.
     */
    boolean eliminarEstado(Long id);

    /**
     * Actualiza un estado existente en la base de datos.
     * 
     * @param id                Identificador único del estado a actualizar.
     * @param datosActualizados Objeto Estado con los nuevos datos.
     * @return Resultado de la operación de actualización.
     */
    ResultadoEstado actualizarEstado(long id, Estado nuevosDatos);
}
