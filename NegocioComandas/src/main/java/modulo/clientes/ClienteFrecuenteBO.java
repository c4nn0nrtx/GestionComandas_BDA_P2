package modulo.clientes;

import DTOs.nuevos.ClientesFrecuentesDTO;
import entidades.ClienteFrecuente;
import excepciones.NegocioException;
import excepciones.PersistenciaException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.ClienteFrecuenteMapper;

/**
 * Clase de negocio para la gestión de clientes frecuentes. Implementa las
 * reglas de negocio y la comunicación con la capa de persistencia.
 *
 * @author brand
 */
public class ClienteFrecuenteBO implements IClienteFrecuenteBO {

    ClienteFrecuenteMapper frecuenteMapper = new ClienteFrecuenteMapper();

    /**
     * Logger para registrar eventos y errores relevantes de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(ClienteFrecuenteBO.class.getName());

    /**
     * Instancia del DAO que maneja la persistencia de clientes frecuentes.
     */
    private IClienteFrecuenteDAO frecuenteDAO;

    /**
     * Constructor que recibe el DAO como dependencia. Se sigue el principio de
     * inversión de dependencias para facilitar la inyección de dependencias y
     * mejorar la testabilidad.
     *
     * @param frecuenteDAO DAO que se usará para acceder a la base de datos. No
     * debe ser null.
     */
    public ClienteFrecuenteBO(IClienteFrecuenteDAO frecuenteDAO) {
        this.frecuenteDAO = frecuenteDAO;
    }

    /**
     * Agrega un nuevo ingrediente al sistema. Realiza validaciones de negocio
     * sobre el DTO de entrada y lo convierte a una entidad antes de persistirlo
     * utilizando el DAO. Finalmente, convierte la entidad persistida a un DTO
     * de salida.
     *
     * @param nuevoCliente DTO que contiene la información del nuevo ingrediente
     * a agregar. No debe ser {@code null}.
     * @return Un {@link IngredienteViejoDTO} que representa el ingrediente
     * agregado.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio durante la validación o si ocurre un error en la capa de
     * persistencia.
     */
    @Override
    public ClientesFrecuentesDTO agregarClienteFrecuente(ClientesFrecuentesDTO nuevoCliente) throws NegocioException {

        // Validar que el DTO de entrada no sea nulo
        if (nuevoCliente == null) {
            throw new NegocioException("El cliente no puede ser nulo.");
        }

        // Validar que el nombre no esté vacío
        if (nuevoCliente.getNombres() == null || nuevoCliente.getNombres().trim().isEmpty()) {
            throw new NegocioException("El nombre del cliente no puede estar vacío.");
        }

        // Validar que el apellido paterno no esté vacío
        if (nuevoCliente.getaPaterno() == null || nuevoCliente.getaPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno del cliente no puede estar vacío.");
        }

        // Validar que el correo no esté vacío
        if (nuevoCliente.getCorreo() == null || nuevoCliente.getCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo del cliente no puede estar vacío.");
        }

        // Validar que el teléfono del cliente no esté vacío
        if (nuevoCliente.getTelefono() == null || nuevoCliente.getTelefono().trim().isEmpty()) {
            throw new NegocioException("El el telefono del cliente no puede estar vacío.");
        }

        // Validar que no exista ya un ingrediente con el mismo nombre y correo
        validarNombreConCorreo(nuevoCliente.getNombres(),
                nuevoCliente.getCorreo());

        // Validar que no exista ya un cliente con el mismo nombre y telefono
        validarNombreConTelefono(nuevoCliente.getNombres(),
                nuevoCliente.getTelefono());

        // Convertir el DTO de entrada a la entidad Ingrediente
        ClienteFrecuente frecuente = ClienteFrecuenteMapper.dtoToEntity(nuevoCliente);

        try {
            // Persistir la entidad utilizando el DAO y convertir el resultado a DTO de salida
            return ClienteFrecuenteMapper.entityToDTO(frecuenteDAO.agregar(frecuente));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al agregar cliente", ex);
            // Lanzar una excepción de negocio encapsulando la excepción de persistencia
            throw new NegocioException("Error al agregar cliente", ex);
        }
    }

    /**
     * Obtiene una lista de clientes frecuentes cuyo nombre coincide con el
     * proporcionado.
     *
     * @param nombre Nombre del cliente a buscar.
     * @return Lista de clientes con el nombre especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    @Override
    public List<ClientesFrecuentesDTO> obtenerClientesPorNombre(String nombre) throws NegocioException {
        try {
            // Obtener la lista de entidades por nombre utilizando el DAO y convertirlas a una lista de DTOs
            return ClienteFrecuenteMapper.entityListToDTOList(frecuenteDAO.obtenerPorNombre(nombre));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por nombre", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por nombre", ex);
        }
    }

    /**
     * Obtiene una lista de clientes frecuentes cuyo teléfono coincide con el
     * proporcionado.
     *
     * @param telefono Número de teléfono del cliente a buscar.
     * @return Lista de clientes con el teléfono especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    @Override
    public List<ClientesFrecuentesDTO> obtenerClientesPorTelefono(String telefono) throws NegocioException {
        try {
            // Obtener la lista de entidades por nombre utilizando el DAO y convertirlas a una lista de DTOs
            return ClienteFrecuenteMapper.entityListToDTOList(frecuenteDAO.obtenerClientePorTelefono(telefono));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por telefono", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por telefono", ex);
        }
    }

    /**
     * Obtiene una lista de clientes frecuentes cuyo correo electrónico coincide
     * con el proporcionado.
     *
     * @param correo Correo electrónico del cliente a buscar.
     * @return Lista de clientes con el correo especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    @Override
    public List<ClientesFrecuentesDTO> obtenerClientesPorCorreo(String correo) throws NegocioException {
        try {
            // Obtener la lista de entidades por nombre utilizando el DAO y convertirlas a una lista de DTOs
            return ClienteFrecuenteMapper.entityListToDTOList(frecuenteDAO.obtenerClientePorTelefono(correo));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por correo", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por correo", ex);
        }
    }

    /**
     * Obtiene la lista completa de clientes frecuentes registrados en el
     * sistema.
     *
     * @return Lista de todos los clientes frecuentes.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    @Override
    public List<ClientesFrecuentesDTO> obtenerTodos() throws NegocioException {
        try {
            // Obtener la lista de entidades utilizando el DAO y convertirlas a una lista de DTOs
            return ClienteFrecuenteMapper.entityListToDTOList(frecuenteDAO.obtenerTodos());
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener todos los clientes", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener todos los clientes", ex);
        }
    }

    /**
     * Valida si ya existe un cliente con el mismo nombre y correo en el
     * sistema.
     *
     * @param nombre Nombre del cliente a validar.
     * @param correo Correo electrónico del cliente a validar.
     * @throws NegocioException Si ya existe un cliente con el mismo nombre y
     * correo.
     */
    private void validarNombreConCorreo(String nombre, String correo) throws NegocioException {
        try {
            // Obtener todos los ingredientes para realizar la validación
            List<ClienteFrecuente> frecuentes = frecuenteDAO.obtenerTodos();

            // Iterar sobre la lista de ingredientes para verificar duplicados
            for (ClienteFrecuente frecuente : frecuentes) {
                if (frecuente.getNombres().equals(nombre)
                        && frecuente.getCorreo().equals(correo)) {
                    throw new NegocioException("Ya existe un cliente con el mismo nombre y correo.");
                }
            }
        } catch (PersistenciaException pe) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al validar el nombre y correo existente", pe);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al validar el nombre y correo existente");
        }
    }

    /**
     * Valida si ya existe un cliente con el mismo nombre y teléfono en el
     * sistema.
     *
     * @param nombre Nombre del cliente a validar.
     * @param telefono Teléfono del cliente a validar.
     * @throws NegocioException Si ya existe un cliente con el mismo nombre y
     * teléfono.
     */
    private void validarNombreConTelefono(String nombre, String telefono) throws NegocioException {
        try {
            // Obtener todos los ingredientes para realizar la validación
            List<ClienteFrecuente> frecuentes = frecuenteDAO.obtenerTodos();

            // Iterar sobre la lista de ingredientes para verificar duplicados
            for (ClienteFrecuente frecuente : frecuentes) {
                if (frecuente.getNombres().equals(nombre)
                        && frecuente.getCorreo().equals(telefono)) {
                    throw new NegocioException("Ya existe un cliente con el mismo nombre y telefono.");
                }
            }
        } catch (PersistenciaException pe) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al validar el nombre y telefono existente", pe);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al validar el nombre y telefono existente");
        }
    }
}
