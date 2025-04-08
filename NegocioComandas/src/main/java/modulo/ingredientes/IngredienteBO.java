/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.ingredientes;

import DTOs.nuevos.IngredienteNuevoDTO;
import DTOs.viejos.IngredienteViejoDTO;
import ENUMs.UnidadMedida;
import entidades.Ingrediente;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.IngredienteMapper;

/**
 * La clase {@code IngredienteBO} implementa la interfaz {@link IIngredienteBO}
 * y contiene la lógica de negocio para la gestión de ingredientes. Actúa como
 * una capa intermedia entre la capa de presentación (UI) y la capa de acceso
 * a datos (DAO). Realiza validaciones de negocio, conversiones entre DTOs y
 * entidades, y maneja las excepciones específicas del dominio.
 * @author Beto_
 */
public class IngredienteBO implements IIngredienteBO {

    /**
     * Mapper para convertir entre entidades {@link Ingrediente} y DTOs
     * (Data Transfer Objects).
     */
    IngredienteMapper ingredienteMapper = new IngredienteMapper();

    /**
     * Logger para registrar eventos y errores relevantes de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(IngredienteBO.class.getName());

    /**
     * Instancia del DAO que maneja la persistencia de ingredientes.
     */
    private IIngredienteDAO ingredienteDAO;

    /**
     * Constructor que recibe el DAO como dependencia. Se sigue el principio de
     * inversión de dependencias para facilitar la inyección de dependencias
     * y mejorar la testabilidad.
     *
     * @param ingredienteDAO DAO que se usará para acceder a la base de datos.
     * No debe ser {@code null}.
     */
    public IngredienteBO(IIngredienteDAO ingredienteDAO) {
        this.ingredienteDAO = ingredienteDAO;
    }

    /**
     * Agrega un nuevo ingrediente al sistema. Realiza validaciones de negocio
     * sobre el DTO de entrada y lo convierte a una entidad antes de persistirlo
     * utilizando el DAO. Finalmente, convierte la entidad persistida a un DTO
     * de salida.
     *
     * @param ingredienteNuevo DTO que contiene la información del nuevo
     * ingrediente a agregar. No debe ser {@code null}.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * agregado.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio durante la validación o si ocurre un
     * error en la capa de persistencia.
     */
    @Override
    public IngredienteViejoDTO agregarIngrediente(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException {
        // Validar que el DTO de entrada no sea nulo
        if (ingredienteNuevo == null) {
            throw new NegocioException("El ingrediente no puede ser nulo.");
        }
        // Validar que el nombre no esté vacío
        if (ingredienteNuevo.getNombre() == null || ingredienteNuevo.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        // Validar que la unidad de medida no sea nula
        if (ingredienteNuevo.getUnidadMedida() == null) {
            throw new NegocioException("La unidad de medida del ingrediente no puede ser nula.");
        }

        // Validar que no exista ya un ingrediente con el mismo nombre y unidad de medida
        validarNombreConUnidadMedida(ingredienteNuevo.getNombre(),
                ingredienteNuevo.getUnidadMedida());

        // Convertir el DTO de entrada a la entidad Ingrediente
        Ingrediente ingrediente = IngredienteMapper.toEntity(ingredienteNuevo);

        try {
            // Persistir la entidad utilizando el DAO y convertir el resultado a DTO de salida
            return IngredienteMapper.toViejoDTO(ingredienteDAO.agregar(ingrediente));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al agregar ingrediente", ex);
            // Lanzar una excepción de negocio encapsulando la excepción de persistencia
            throw new NegocioException("Error al agregar ingrediente", ex);
        }
    }

    /**
     * Elimina un ingrediente del sistema basado en su ID. Realiza una validación
     * para asegurar que el ingrediente a eliminar exista.
     *
     * @param id El ID del ingrediente a eliminar. Debe ser un valor positivo.
     * @return {@code true} si el ingrediente fue eliminado exitosamente;
     * {@code false} en caso contrario.
     * @throws NegocioException Si el ingrediente con el ID especificado no
     * existe o si ocurre un error en la capa de
     * persistencia.
     */
    @Override
    public boolean eliminarIngrediente(Long id) throws NegocioException {
        // Validar que el ingrediente a eliminar exista
        if (obtenerPorId(id) == null) {
            throw new NegocioException("Error: El ingrediente a eliminar no existe");
        }
        try {
            // Eliminar el ingrediente utilizando el DAO
            return ingredienteDAO.eliminar(id);
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al eliminar ingrediente", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al eliminar ingrediente", ex);
        }
    }

    /**
     * Actualiza la información de un ingrediente existente. Realiza validaciones
     * sobre el DTO de entrada y lo convierte a una entidad antes de actualizarla
     * utilizando el DAO. Finalmente, convierte la entidad actualizada a un DTO
     * de salida.
     *
     * @param ingredienteViejo DTO que contiene la información actualizada del
     * ingrediente. No debe ser {@code null}.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * actualizado.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio durante la validación o si ocurre un
     * error en la capa de persistencia.
     */
    @Override
    public IngredienteViejoDTO actualizarIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException {
        // Validaciones de campos no nulos
        if (ingredienteViejo == null) {
            throw new NegocioException("El ingrediente no puede ser nulo.");
        }
        if (ingredienteViejo.getId() == null) {
            throw new NegocioException("El id del ingrediente no puede ser nulo.");
        }
        if (ingredienteViejo.getNombre() == null || ingredienteViejo.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (ingredienteViejo.getUnidadMedida() == null) {
            throw new NegocioException("La unidad de medida del ingrediente no puede ser nula.");
        }

        // Validar que el ingrediente a actualizar exista
        if (obtenerPorId(ingredienteViejo.getId()) == null) {
            throw new NegocioException("Error: El ingrediente a actualizar no existe");
        }

        // Validar que no exista otro ingrediente con el mismo nombre y unidad de medida (excluyendo el actual)
        validarNombreConUnidadMedida(ingredienteViejo.getNombre(),
                ingredienteViejo.getUnidadMedida());

        // Convertir el DTO de entrada a la entidad Ingrediente
        Ingrediente ingrediente = IngredienteMapper.toEntity(ingredienteViejo);

        try {
            // Actualizar la entidad utilizando el DAO y convertir el resultado a DTO de salida
            return IngredienteMapper.toViejoDTO(ingredienteDAO.actualizar(ingrediente));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al actualizar ingrediente", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al actualizar ingrediente", ex);
        }
    }

    /**
     * Actualiza el stock de un ingrediente existente.
     *
     * @param ingredienteViejo DTO que contiene el ID y el nuevo stock del
     * ingrediente.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * actualizado.
     * @throws NegocioException Si el ID del ingrediente es nulo o si ocurre un
     * error en la capa de persistencia.
     */
    @Override
    public IngredienteViejoDTO actualizarStockIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException {
        if (ingredienteViejo.getStock() == null) {
            throw new NegocioException("El stock del ingrediente no puede ser nulo.");
        }
        if (ingredienteViejo.getId() == null) {
            throw new NegocioException("El ID del ingrediente no puede ser nulo para actualizar el stock.");
        }

        // Convertir DTO a entidad
        Ingrediente ingrediente = IngredienteMapper.toEntity(ingredienteViejo);
        try {
            // Actualizar la entidad utilizando el DAO y convertir el resultado a DTO de salida
            return IngredienteMapper.toViejoDTO(ingredienteDAO.actualizar(ingrediente));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al actualizar ingrediente", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al actualizar ingrediente", ex);
        }
    }

    /**
     * Obtiene un ingrediente del sistema basado en su ID.
     *
     * @param id El ID del ingrediente a buscar. Debe ser un valor positivo.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * encontrado, o {@code null} si no existe.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    @Override
    public IngredienteViejoDTO obtenerPorId(Long id) throws NegocioException {
        try {
            // Obtener la entidad utilizando el DAO y convertirla a DTO de salida
            return IngredienteMapper.toViejoDTO(ingredienteDAO.obtenerPorId(id));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener ingrediente por ID", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener ingrediente por ID", ex);
        }
    }

    /**
     * Obtiene una lista de todos los ingredientes del sistema.
     *
     * @return Una {@link List} de {@link IngredienteViejoDTO} que representan
     * todos los ingredientes encontrados. Puede ser una lista vacía si no
     * hay ingredientes.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    @Override
    public List<IngredienteViejoDTO> obtenerTodos() throws NegocioException {
        try {
            // Obtener la lista de entidades utilizando el DAO y convertirlas a una lista de DTOs
            return IngredienteMapper.toViejoDTOList(ingredienteDAO.obtenerTodos());
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener todos los ingredientes", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener todos los ingredientes", ex);
        }
    }

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
    @Override
    public List<IngredienteViejoDTO> obtenerPorNombre(String nombre) throws NegocioException {
        try {
            // Obtener la lista de entidades por nombre utilizando el DAO y convertirlas a una lista de DTOs
            return IngredienteMapper.toViejoDTOList(ingredienteDAO.obtenerPorNombre(nombre));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener ingredientes por nombre", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener ingredientes por nombre", ex);
        }
    }

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
    @Override
    public List<IngredienteViejoDTO> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws NegocioException {
        try {
            // Obtener la lista de entidades por unidad de medida utilizando el DAO y convertirlas a una lista de DTOs
            return IngredienteMapper.toViejoDTOList(ingredienteDAO.obtenerPorUnidadMedida(unidadMedida));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener ingredientes por unidad de medida", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener ingredientes por unidad de medida", ex);
        }
    }

    /**
     * Valida si ya existe un ingrediente con el mismo nombre y unidad de medida
     * en el sistema.
     *
     * @param nombre       El nombre del ingrediente a validar.
     * @param unidadMedida La unidad de medida del ingrediente a validar.
     * @throws NegocioException Si ya existe un ingrediente con el mismo nombre
     * y unidad de medida.
     */
    private void validarNombreConUnidadMedida(String nombre, UnidadMedida unidadMedida) throws NegocioException {
        try {
            // Obtener todos los ingredientes para realizar la validación
            List<Ingrediente> ingredientes = ingredienteDAO.obtenerTodos();

            // Iterar sobre la lista de ingredientes para verificar duplicados
            for (Ingrediente ingrediente : ingredientes) {
                if (ingrediente.getNombre().equalsIgnoreCase(nombre)
                        && ingrediente.getUnidadMedida() == unidadMedida) {
                    throw new NegocioException("Ya existe un ingrediente con el mismo nombre y unidad de medida.");
                }
            }
        } catch (PersistenciaException pe) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al validar el nombre y unidad existente", pe);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al validar el nombre y unidad existente");
        }
    }
}
