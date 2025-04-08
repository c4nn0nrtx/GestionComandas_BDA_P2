/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.comandas;

import DTOs.nuevos.MesaNuevoDTO;
import DTOs.viejos.MesaViejoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.MesaMapper;

/**
 * La clase {@code MesaBO} implementa la interfaz {@link IMesaBO} y contiene
 * la lógica de negocio para la gestión de las mesas. Actúa como una capa
 * intermedia entre la capa de presentación (UI) y la capa de acceso a datos
 * (DAO). Realiza validaciones de negocio y conversiones entre DTOs y entidades.
 * @author Beto_
 */
public class MesaBO implements IMesaBO{
    /**
     * Logger para registrar eventos y errores relevantes de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(MesaBO.class.getName());

    /**
     * Instancia del DAO que maneja la persistencia de las mesas.
     */
    private IMesaDAO mesaDAO;

    /**
     * Constructor que recibe el DAO como dependencia. Se sigue el principio de
     * inversión de dependencias para facilitar la inyección de dependencias
     * y mejorar la testabilidad.
     *
     * @param mesaDAO DAO que se usará para acceder a la base de datos.
     * No debe ser {@code null}.
     */
    public MesaBO(IMesaDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
    }

    /**
     * Agrega una nueva mesa al sistema. Realiza validaciones de negocio sobre
     * el DTO de entrada y lo convierte a una entidad antes de persistirlo
     * utilizando el DAO. Finalmente, convierte la entidad persistida a un DTO
     * de salida.
     *
     * @param mesaNueva DTO que contiene la información de la nueva mesa a
     * agregar. No debe ser {@code null}.
     * @return Un {@link MesaViejoDTO} que representa la mesa agregada.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio durante la validación o si ocurre un
     * error en la capa de persistencia.
     */
    @Override
    public MesaViejoDTO agregar(MesaNuevoDTO mesaNueva) throws NegocioException {
        try {
            // Validar que el número de mesa sea positivo
            if (mesaNueva.getNumeroMesa() <= 0) {
                throw new NegocioException("Error: número de mesa negativo o cero");
            }

            // Convertir el DTO a la entidad Mesa y persistirla utilizando el DAO
            return MesaMapper.toViejoDTO(mesaDAO.agregar(MesaMapper.toEntity(mesaNueva)));

        } catch (PersistenciaException pe) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al agregar la mesa", pe);
            // Lanzar una excepción de negocio encapsulando la excepción de persistencia
            throw new NegocioException("Error al agregar la mesa", pe);
        }
    }

    /**
     * Obtiene una mesa del sistema basado en su identificador único.
     *
     * @param id El identificador único (ID) de la mesa a buscar. Debe ser un
     * valor positivo.
     * @return Un {@link MesaViejoDTO} que representa la mesa encontrada, o
     * {@code null} si no existe.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    @Override
    public MesaViejoDTO obtenerPorId(Long id) throws NegocioException {
        try {
            // Obtener la entidad Mesa por su ID utilizando el DAO y convertirla a DTO
            return MesaMapper.toViejoDTO(mesaDAO.obtenerPorId(id));
        } catch (PersistenciaException pe) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener mesa por id", pe);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener mesa por id", pe);
        }
    }

    /**
     * Obtiene una mesa del sistema basado en su número de mesa.
     *
     * @param numeroMesa El número de mesa único a buscar. Debe ser un valor
     * positivo.
     * @return Un {@link MesaViejoDTO} que representa la mesa encontrada, o
     * {@code null} si no existe.
     * @throws NegocioException Si el número de mesa no es válido o si ocurre
     * un error en la capa de persistencia.
     */
    @Override
    public MesaViejoDTO obtenerPorNumeroMesa(Integer numeroMesa) throws NegocioException {
        try {
            // Validar que el número de mesa sea positivo
            if (numeroMesa <= 0) {
                throw new NegocioException("Error: número de mesa negativo o cero");
            }

            // Obtener la entidad Mesa por su número de mesa utilizando el DAO y convertirla a DTO
            return MesaMapper.toViejoDTO(mesaDAO.obtenerPorNumeroMesa(numeroMesa));

        } catch (PersistenciaException pe) {
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener la mesa", pe);
        }
    }

    /**
     * Obtiene una lista de todas las mesas almacenadas en el sistema.
     *
     * @return Una {@link List} de {@link MesaViejoDTO} que representan todas
     * las mesas encontradas. Puede ser una lista vacía si no hay mesas.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    @Override
    public List<MesaViejoDTO> obtenerTodas() throws NegocioException {
        try {
            // Obtener la lista de entidades Mesa utilizando el DAO y convertirlas a una lista de DTOs
            return MesaMapper.toViejoDTOList(mesaDAO.obtenerTodas());
        } catch (PersistenciaException pe) {
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener todas las mesas", pe);
        }
    }
}
