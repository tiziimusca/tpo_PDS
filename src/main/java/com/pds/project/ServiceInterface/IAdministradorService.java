package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Implementation.AdministradorServiceImpl.ResultadoAdministrador;
import com.pds.project.Models.Administrador;

public interface IAdministradorService {

    /**
     * Obtiene la lista de todos los administradores registrados.
     * 
     * @return Lista de administradores.
     */
    List<Administrador> getAdministradores();

    /**
     * Guarda o actualiza un administrador en la base de datos.
     * 
     * @param administrador Objeto Administrador a guardar.
     * @return true si la operación fue exitosa, false si hubo un error.
     */
    ResultadoAdministrador guardarAdministrador(Administrador administrador);

    /**
     * Busca un administrador por su ID.
     * 
     * @param id Identificador único del administrador.
     * @return Administrador.
     */
    Administrador getAdministradorById(Long id);

    /**
     * Elimina un administrador por su ID.
     * 
     * @param id Identificador del administrador a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró el
     *         administrador.
     */
    boolean eliminarAdministrador(Long id);

    /**
     * Actualiza un administrador existente en la base de datos.
     * 
     * @param id                Identificador único del administrador a actualizar.
     * @param datosActualizados Objeto Administrador con los nuevos datos.
     * @return Resultado de la operación de actualización.
     */
    ResultadoAdministrador actualizarAdministrador(long id, Administrador nuevosDatos);
}
