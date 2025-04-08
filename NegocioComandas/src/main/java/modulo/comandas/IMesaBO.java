/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import DTOs.nuevos.MesaNuevoDTO;
import DTOs.viejos.MesaViejoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * La interfaz {@code IMesaBO} define las operaciones de lógica de negocio
 * (Business Object) para la gestión de las mesas. Esta interfaz proporciona
 * métodos para agregar y consultar información de las mesas, abstrayendo
 * la capa de acceso a datos (DAO) de la capa de presentación (UI).
 * @author Beto_
 */
public interface IMesaBO {
    /**
     * Agrega una nueva mesa al sistema. Realiza las validaciones de negocio
     * necesarias antes de delegar la persistencia a la capa DAO.
     *
     * @param mesaNueva DTO (Data Transfer Object) que contiene la información
     * de la nueva mesa a agregar.
     * @return Un {@link MesaViejoDTO} que representa la mesa agregada.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio o si ocurre un error en la capa de persistencia.
     */
    public MesaViejoDTO agregar(MesaNuevoDTO mesaNueva) throws NegocioException;

    /**
     * Obtiene una mesa del sistema basado en su identificador único.
     *
     * @param id El identificador único (ID) de la mesa a buscar.
     * @return Un {@link MesaViejoDTO} que representa la mesa encontrada,
     * o {@code null} si no existe.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    public MesaViejoDTO obtenerPorId(Long id) throws NegocioException;

    /**
     * Obtiene una mesa del sistema basado en su número de mesa.
     *
     * @param numeroMesa El número de mesa único a buscar.
     * @return Un {@link MesaViejoDTO} que representa la mesa encontrada,
     * o {@code null} si no existe.
     * @throws NegocioException Si el número de mesa no es válido o si ocurre
     * un error en la capa de persistencia.
     */
    public MesaViejoDTO obtenerPorNumeroMesa(Integer numeroMesa) throws NegocioException;

    /**
     * Obtiene una lista de todas las mesas almacenadas en el sistema.
     *
     * @return Una {@link List} de {@link MesaViejoDTO} que representan todas
     * las mesas encontradas. Puede ser una lista vacía si no hay mesas.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    public List<MesaViejoDTO> obtenerTodas() throws NegocioException;
}
