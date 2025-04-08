/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import ENUMs.EstadoComanda;
import entidades.Comanda;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * La interfaz {@code IComandaDAO} define las operaciones de acceso a datos
 * (Data Access Object) para la entidad {@link Comanda}. Proporciona métodos
 * para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y consultas
 * específicas relacionadas con las comandas en el sistema. También incluye
 * métodos para gestionar la relación entre las comandas y los clientes.
 * @author Beto_
 */
public interface IComandaDAO {
    /**
     * Agrega una nueva comanda al sistema de persistencia.
     *
     * @param comanda El objeto {@link Comanda} que se va a persistir. No debe ser {@code null}.
     * @return El objeto {@link Comanda} que ha sido guardado en la base de datos,
     * incluyendo cualquier identificador generado.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de persistencia, como problemas de conexión,
     * errores de transacción o violación de restricciones.
     */
    public Comanda agregar(Comanda comanda) throws PersistenciaException;

    /**
     * Elimina una comanda del sistema de persistencia basado en su identificador único.
     *
     * @param id El identificador único (ID) de la comanda que se va a eliminar.
     * Debe ser un valor positivo.
     * @return {@code true} si la comanda con el ID especificado fue eliminada
     * exitosamente; {@code false} si no se encontró ninguna comanda con
     * ese ID.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de eliminación, como problemas de conexión
     * a la base de datos o si la transacción falla.
     */
    public boolean eliminar(Long id) throws PersistenciaException;

    /**
     * Actualiza la información de una comanda existente en el sistema de persistencia.
     *
     * @param comanda El objeto {@link Comanda} con la información actualizada.
     * La comanda debe existir previamente en el sistema.
     * @return El objeto {@link Comanda} que ha sido actualizado y persistido.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de actualización, como problemas de conexión
     * a la base de datos, si la comanda no existe,
     * o si la transacción falla.
     */
    public Comanda actualizar(Comanda comanda) throws PersistenciaException;

    /**
     * Obtiene una comanda del sistema de persistencia basado en su identificador único.
     *
     * @param id El identificador único (ID) de la comanda que se va a buscar.
     * Debe ser un valor positivo.
     * @return El objeto {@link Comanda} encontrado con el ID especificado, o
     * {@code null} si no se encuentra ninguna comanda con ese ID.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión
     * a la base de datos.
     */
    public Comanda obtenerPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene una comanda del sistema de persistencia basado en su folio único.
     *
     * @param folio El folio único de la comanda que se va a buscar.
     * @return El objeto {@link Comanda} cuyo folio coincide con el valor
     * proporcionado.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión a la base de datos,
     * o si no se encuentra ninguna comanda con el folio especificado.
     */
    public Comanda obtenerPorFolio(String folio) throws PersistenciaException;

    /**
     * Obtiene una lista de comandas creadas dentro de un rango de fechas especificado.
     *
     * @param fechaInicio La fecha de inicio del rango (inclusive).
     * @param fechaFin La fecha de fin del rango (inclusive).
     * @return Una {@link List} de objetos {@link Comanda} que se crearon dentro
     * del rango de fechas especificado. Puede devolver una lista vacía si no
     * se encuentran comandas en ese rango.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión a la base de datos.
     */
    public List<Comanda> obtenerPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException;

    /**
     * Obtiene una lista de todas las comandas almacenadas en el sistema de persistencia.
     *
     * @return Una {@link List} que contiene todos los objetos {@link Comanda}
     * presentes en la base de datos. Puede devolver una lista vacía si no hay
     * comandas.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de obtención, como problemas de conexión
     * a la base de datos.
     */
    public List<Comanda> obtenerTodos() throws PersistenciaException;

    /**
     * Asigna un cliente a una comanda específica. Si la comanda ya tiene un
     * cliente asignado, este se desasigna antes de asignar el nuevo cliente.
     *
     * @param idComanda El identificador único (ID) de la comanda a la que se
     * asignará el cliente.
     * @param idCliente El identificador único (ID) del cliente que se asignará
     * a la comanda.
     * @return {@code true} si la asignación se realizó correctamente.
     * @throws PersistenciaException Si ocurre algún error durante la operación,
     * como problemas de conexión a la base de datos,
     * si la comanda o el cliente no existen, o si la transacción falla.
     */
    public boolean asignarCliente(Long idComanda, Long idCliente) throws PersistenciaException;

    /**
     * Desasigna el cliente asociado a una comanda específica.
     *
     * @param idComanda El identificador único (ID) de la comanda de la cual se
     * desasignará el cliente.
     * @return {@code true} si la desasignación se realizó correctamente.
     * @throws PersistenciaException Si ocurre algún error durante la operación,
     * como problemas de conexión a la base de datos,
     * o si la comanda no existe.
     */
    public boolean desasignarCliente(Long idComanda) throws PersistenciaException;
}
