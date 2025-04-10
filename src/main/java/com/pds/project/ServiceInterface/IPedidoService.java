package com.pds.project.ServiceInterface;

import java.util.List;

import com.pds.project.Implementation.PedidoServiceImpl.ResultadoPedido;
import com.pds.project.Models.Pedido;

public interface IPedidoService {

    /**
     * Obtiene la lista de todos los pedidos registrados.
     * 
     * @return Lista de pedidos.
     */
    List<Pedido> getPedidos();

    /**
     * Guarda o actualiza un pedido en la base de datos.
     * 
     * @param pedido Objeto Pedido a guardar.
     * @return true si la operación fue exitosa, false si hubo un error.
     */
    ResultadoPedido guardarPedido(Pedido pedido);

    /**
     * Busca un pedido por su ID.
     * 
     * @param id Identificador único del pedido.
     * @return Pedido.
     */
    Pedido getPedidoById(Long id);

    /**
     * Elimina un pedido por su ID.
     * 
     * @param id Identificador del pedido a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró el
     *         pedido.
     */
    boolean eliminarPedido(Long id);

    /**
     * Actualiza un pedido existente en la base de datos.
     * 
     * @param id                Identificador único del pedido a actualizar.
     * @param datosActualizados Objeto Pedido con los nuevos datos.
     * @return Resultado de la operación de actualización.
     */
    ResultadoPedido actualizarPedido(long id, Pedido nuevosDatos);
}
