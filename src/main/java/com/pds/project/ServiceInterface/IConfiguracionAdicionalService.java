package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Implementation.ConfiguracionAdicionalServiceImpl.ResultadoConfiguracionAdicional;
import com.pds.project.Models.ConfiguracionAdicional;

public interface IConfiguracionAdicionalService {

    /**
     * Obtiene la lista de todas las configuraciones adicionales registradas.
     * 
     * @return Lista de configuraciones adicional.
     */
    List<ConfiguracionAdicional> getConfiguracionesAdicional();

    /**
     * Guarda o actualiza una configuracion adicional en la base de datos.
     * 
     * @param config Objeto ConfiguracionAdicional a guardar.
     * @return true si la operación fue exitosa, false si hubo un error.
     */
    ResultadoConfiguracionAdicional guardarConfiguracionAdicional(ConfiguracionAdicional config);

    /**
     * Busca una configuracion adicional por su ID.
     * 
     * @param id Identificador único de la configuracion adicional.
     * @return ConfiguracionAdicional.
     */
    ConfiguracionAdicional getConfiguracionAdicionalById(Long id);

    /**
     * Elimina una configuracion adicional por su ID.
     * 
     * @param id Identificador de la configuracion adicional a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró la
     *         configuracion adicional.
     */
    boolean eliminarConfiguracionAdicional(Long id);

    /**
     * Actualiza una configuracion adicional existente en la base de datos.
     * 
     * @param id                Identificador único de la configuracion adicional a
     *                          actualizar.
     * @param datosActualizados Objeto ConfiguracionAdicional con los nuevos datos.
     * @return Resultado de la operación de actualización.
     */
    ResultadoConfiguracionAdicional actualizarConfiguracionAdicional(long id, ConfiguracionAdicional nuevosDatos);
}
