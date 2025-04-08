/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import entidades.Mesa;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * La interfaz {@code IMesaDAO} define las operaciones de acceso a datos
 * (Data Access Object) para la entidad {@link Mesa}. Proporciona métodos
 * para realizar operaciones básicas de persistencia y consultas específicas
 * relacionadas con las mesas en el sistema.
 * @author Beto_
 */
public interface IMesaDAO {
    /**
     * Agrega una nueva mesa al sistema de persistencia.
     *
     * @param mesa El objeto {@link Mesa} que se va a persistir. No debe ser {@code null}.
     * @return El objeto {@link Mesa} que ha sido guardado en la base de datos,
     * incluyendo cualquier identificador generado.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de persistencia, como problemas de conexión,
     * errores de transacción o violación de restricciones.
     */
    public Mesa agregar(Mesa mesa) throws PersistenciaException;

    /**
     * Obtiene una mesa del sistema de persistencia basado en su identificador único.
     *
     * @param id El identificador único (ID) de la mesa que se va a buscar.
     * Debe ser un valor positivo.
     * @return El objeto {@link Mesa} encontrado con el ID especificado, o {@code null}
     * si no se encuentra ninguna mesa con ese ID.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión a la base de datos.
     */
    public Mesa obtenerPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene una mesa del sistema de persistencia basado en su número de mesa.
     *
     * @param numeroMesa El número de mesa único que se va a buscar.
     * @return El objeto {@link Mesa} cuyo número de mesa coincide con el valor
     * proporcionado.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión a la base de datos,
     * o si no se encuentra ninguna mesa con el número especificado.
     */
    public Mesa obtenerPorNumeroMesa(Integer numeroMesa) throws PersistenciaException;

    /**
     * Obtiene una lista de todas las mesas almacenadas en el sistema de persistencia.
     *
     * @return Una {@link List} que contiene todos los objetos {@link Mesa} presentes
     * en la base de datos. Puede devolver una lista vacía si no hay mesas.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de obtención, como problemas de conexión a la base de datos.
     */
    public List<Mesa> obtenerTodas() throws PersistenciaException;
}
