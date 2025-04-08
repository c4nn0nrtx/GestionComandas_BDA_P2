/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.ingredientes;

import DTOs.nuevos.IngredienteNuevoDTO;
import DTOs.viejos.IngredienteViejoDTO;
import ENUMs.UnidadMedida;
import excepciones.NegocioException;
import java.util.List;

/**
 * La interfaz {@code IIngredienteBO} define las operaciones de lógica de negocio
 * (Business Object) para la gestión de ingredientes. Esta interfaz proporciona
 * métodos para agregar, eliminar, actualizar y consultar información de los
 * ingredientes, abstrayendo la capa de acceso a datos (DAO) de la capa de
 * presentación (UI). También define métodos específicos para actualizar el
 * stock y realizar búsquedas por nombre y unidad de medida.
 * @author Beto_
 */
public interface IIngredienteBO {
    /**
     * Agrega un nuevo ingrediente al sistema. Realiza validaciones de negocio
     * necesarias antes de delegar la persistencia a la capa DAO.
     *
     * @param ingredienteNuevo DTO (Data Transfer Object) que contiene la
     * información del nuevo ingrediente a agregar.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * agregado.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio o si ocurre un error en la capa de persistencia.
     */
    public IngredienteViejoDTO agregarIngrediente(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException;

    /**
     * Elimina un ingrediente del sistema basado en su identificador único.
     * Realiza validaciones de negocio antes de intentar la eliminación.
     *
     * @param id El identificador único (ID) del ingrediente a eliminar.
     * @return {@code true} si el ingrediente fue eliminado exitosamente;
     * {@code false} en caso contrario.
     * @throws NegocioException Si el ingrediente con el ID especificado no
     * existe o si ocurre un error en la capa de persistencia.
     */
    public boolean eliminarIngrediente(Long id) throws NegocioException;

    /**
     * Actualiza la información general de un ingrediente existente en el sistema.
     * Realiza validaciones de negocio sobre la información a actualizar.
     *
     * @param ingredienteViejo DTO que contiene la información actualizada del
     * ingrediente. Debe incluir el ID del ingrediente a actualizar.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * actualizado.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio o si ocurre un error en la capa de persistencia.
     */
    public IngredienteViejoDTO actualizarIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException;

    /**
     * Actualiza únicamente el stock de un ingrediente existente en el sistema.
     *
     * @param ingredienteViejo DTO que contiene el ID del ingrediente y el nuevo
     * valor del stock.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente con
     * el stock actualizado.
     * @throws NegocioException Si el ID del ingrediente no es válido o si
     * ocurre un error en la capa de persistencia.
     */
    public IngredienteViejoDTO actualizarStockIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException;

    /**
     * Obtiene un ingrediente del sistema basado en su identificador único.
     *
     * @param id El identificador único (ID) del ingrediente a buscar.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * encontrado, o {@code null} si no existe.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    public IngredienteViejoDTO obtenerPorId(Long id) throws NegocioException;

    /**
     * Obtiene una lista de todos los ingredientes almacenados en el sistema.
     *
     * @return Una {@link List} de {@link IngredienteViejoDTO} que representan
     * todos los ingredientes encontrados. Puede ser una lista vacía si no
     * hay ingredientes.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    public List<IngredienteViejoDTO> obtenerTodos() throws NegocioException;

    /**
     * Obtiene una lista de ingredientes cuyo nombre coincide (parcialmente, sin
     * distinción de mayúsculas y minúsculas) con el nombre especificado.
     *
     * @param nombre La cadena de texto a buscar en los nombres de los
     * ingredientes.
     * @return Una {@link List} de {@link IngredienteViejoDTO} que representan
     * los ingredientes encontrados. Puede ser una lista vacía si no se
     * encuentran ingredientes con el nombre especificado.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    public List<IngredienteViejoDTO> obtenerPorNombre(String nombre) throws NegocioException;

    /**
     * Obtiene una lista de ingredientes que tienen la unidad de medida
     * especificada.
     *
     * @param unidadMedida La {@link UnidadMedida} por la cual filtrar los
     * ingredientes.
     * @return Una {@link List} de {@link IngredienteViejoDTO} que representan
     * los ingredientes encontrados. Puede ser una lista vacía si no se
     * encuentran ingredientes con la unidad de medida especificada.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    public List<IngredienteViejoDTO> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws NegocioException;
}
