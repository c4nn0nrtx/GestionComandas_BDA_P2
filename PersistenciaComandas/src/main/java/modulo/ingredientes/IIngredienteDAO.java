/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.ingredientes;

import ENUMs.UnidadMedida;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * La interfaz {@code IIngredienteDAO} define las operaciones de acceso a datos
 * (Data Access Object) para la entidad {@link Ingrediente}. Proporciona métodos
 * para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y consultas
 * específicas sobre los ingredientes almacenados en el sistema de persistencia.
 * @author Beto_
 */
public interface IIngredienteDAO {
    /**
     * Agrega un nuevo ingrediente al sistema de persistencia.
     *
     * @param ingrediente El objeto {@link Ingrediente} que se va a agregar.
     * No debe ser {@code null}.
     * @return El objeto {@link Ingrediente} que ha sido persistido, incluyendo
     * cualquier identificador generado por el sistema de persistencia.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de persistencia, como problemas de conexión
     * a la base de datos o violación de restricciones.
     */
    public Ingrediente agregar(Ingrediente ingrediente) throws PersistenciaException;

    /**
     * Elimina un ingrediente del sistema de persistencia basado en su identificador único.
     *
     * @param id El identificador único (ID) del ingrediente que se va a eliminar.
     * Debe ser un valor positivo.
     * @return {@code true} si el ingrediente con el ID especificado fue eliminado
     * exitosamente; {@code false} si no se encontró ningún ingrediente con
     * ese ID.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de eliminación, como problemas de conexión
     * a la base de datos o si la transacción falla.
     */
    public boolean eliminar(Long id) throws PersistenciaException;

    /**
     * Actualiza la información de un ingrediente existente en el sistema de persistencia.
     *
     * @param ingrediente El objeto {@link Ingrediente} con la información actualizada.
     * El ingrediente debe existir previamente en el sistema.
     * @return El objeto {@link Ingrediente} que ha sido actualizado y persistido.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de actualización, como problemas de conexión
     * a la base de datos, si el ingrediente no existe,
     * o si la transacción falla.
     */
    public Ingrediente actualizar(Ingrediente ingrediente) throws PersistenciaException;

    /**
     * Obtiene un ingrediente del sistema de persistencia basado en su identificador único.
     *
     * @param id El identificador único (ID) del ingrediente que se va a buscar.
     * Debe ser un valor positivo.
     * @return El objeto {@link Ingrediente} encontrado con el ID especificado, o
     * {@code null} si no se encuentra ningún ingrediente con ese ID.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión
     * a la base de datos.
     */
    public Ingrediente obtenerPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene una lista de todos los ingredientes almacenados en el sistema de persistencia.
     *
     * @return Una {@link List} que contiene todos los objetos {@link Ingrediente}
     * presentes en el sistema. Puede devolver una lista vacía si no hay
     * ingredientes almacenados.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de obtención, como problemas de conexión
     * a la base de datos.
     */
    public List<Ingrediente> obtenerTodos() throws PersistenciaException;

    /**
     * Obtiene una lista de ingredientes cuyo nombre coincide (parcialmente, sin
     * distinción de mayúsculas y minúsculas) con el nombre especificado.
     *
     * @param nombre La cadena de texto que se utilizará para buscar ingredientes
     * por nombre.
     * @return Una {@link List} que contiene los objetos {@link Ingrediente} cuyo
     * nombre contiene la cadena especificada. Puede devolver una lista
     * vacía si no se encuentran ingredientes con ese nombre.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión
     * a la base de datos.
     */
    public List<Ingrediente> obtenerPorNombre(String nombre) throws PersistenciaException;

    /**
     * Obtiene una lista de ingredientes que tienen la unidad de medida especificada.
     *
     * @param unidadMedida La {@link UnidadMedida} por la cual se van a filtrar
     * los ingredientes. No debe ser {@code null}.
     * @return Una {@link List} que contiene los objetos {@link Ingrediente} que
     * tienen la unidad de medida especificada. Puede devolver una lista
     * vacía si no se encuentran ingredientes con esa unidad de medida.
     * @throws PersistenciaException Si ocurre algún error durante la operación
     * de búsqueda, como problemas de conexión
     * a la base de datos.
     */
    public List<Ingrediente> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws PersistenciaException;
    
    public List<Ingrediente> obtenerPorFiltro(String nombre, UnidadMedida unidadMedida) throws PersistenciaException;
}
