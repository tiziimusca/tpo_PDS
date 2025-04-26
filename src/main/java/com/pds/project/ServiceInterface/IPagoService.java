package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Implementation.PagoServiceImpl.ResultadoPago;
import com.pds.project.Models.Pago;

public interface IPagoService {

    /**
     * Obtiene la lista de todos los pagos registrados.
     * 
     * @return Lista de pagos.
     */
    List<Pago> getPagos();

    /**
     * Guarda o actualiza un pago en la base de datos.
     * 
     * @param pago Objeto Pago a guardar.
     * @return true si la operación fue exitosa, false si hubo un error.
     */
    ResultadoPago guardarPago(Pago pago);

    /**
     * Busca un pago por su ID.
     * 
     * @param id Identificador único del pago.
     * @return Pago.
     */
    Pago getPagoById(Long id);

    /**
     * Elimina un pago por su ID.
     * 
     * @param id Identificador del pago a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró el
     *         pago.
     */
    boolean eliminarPago(Long id);

    /**
     * Actualiza un pago existente en la base de datos.
     * 
     * @param id                Identificador único del pago a actualizar.
     * @param datosActualizados Objeto Pago con los nuevos datos.
     * @return Resultado de la operación de actualización.
     */
    ResultadoPago actualizarPago(long id, Pago nuevosDatos);
}
