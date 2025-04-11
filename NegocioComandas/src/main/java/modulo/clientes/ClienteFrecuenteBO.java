package modulo.clientes;

import DTOs.nuevos.ClienteFrecuenteNuevoDTO;
import DTOs.viejos.ClienteFrecuenteViejoDTO;
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
    public ClienteFrecuenteViejoDTO agregarClienteFrecuente(ClienteFrecuenteNuevoDTO nuevoCliente) throws NegocioException {

        // Validar que el DTO de entrada no sea nulo
        if (nuevoCliente == null) {
            throw new NegocioException("El cliente no puede ser nulo.");
        }

        // Validar que el nombre no esté vacío
        if (nuevoCliente.getNombres() == null || nuevoCliente.getNombres().trim().isEmpty()) {
            throw new NegocioException("El nombre del cliente no puede estar vacío.");
        }
        // Validar que el nombre solo contenga letras (sin números ni caracteres especiales)
        if (!nuevoCliente.getNombres().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new NegocioException("El nombre solo puede contener letras.");
        }

        // Validar que el apellido paterno no esté vacío
        if (nuevoCliente.getaPaterno() == null || nuevoCliente.getaPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno del cliente no puede estar vacío.");
        }
        // Validar que el apellido paterno solo contenga letras
        if (!nuevoCliente.getaPaterno().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new NegocioException("El apellido paterno solo puede contener letras.");
        }

        // Validar que el correo no esté vacío
        if (nuevoCliente.getCorreo() == null || nuevoCliente.getCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo del cliente no puede estar vacío.");
        }
        // Validar formato del correo (que contenga un '@' y no tenga caracteres especiales)
        if (!nuevoCliente.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new NegocioException("El correo electrónico no es válido.");
        }

        // Validar que el teléfono no esté vacío
        if (nuevoCliente.getTelefono() == null || nuevoCliente.getTelefono().trim().isEmpty()) {
            throw new NegocioException("El teléfono del cliente no puede estar vacío.");
        }
        // Validar que el teléfono contenga solo números y tenga exactamente 10 caracteres
        if (!nuevoCliente.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe contener exactamente 10 números.");
        }

        // Validar que las visitas sean un valor válido
        if (nuevoCliente.getVisitas() == null) {
            throw new NegocioException("Las visitas no pueden ser nulas.");
        }
        // Validar que las visitas sean un número positivo
        if (nuevoCliente.getVisitas() < 0) {
            throw new NegocioException("El número de visitas debe ser un valor positivo.");
        }

        // Validar que no exista ya un cliente con el mismo nombre y correo
        validarNombreConCorreo(nuevoCliente.getNombres(), nuevoCliente.getCorreo());

        // Validar que no exista ya un cliente con el mismo nombre y teléfono
        validarNombreConTelefono(nuevoCliente.getNombres(), nuevoCliente.getTelefono());

        // Convertir el DTO de entrada a la entidad ClienteFrecuente
        ClienteFrecuente frecuente = ClienteFrecuenteMapper.nuevodtoToEntity(nuevoCliente);

        try {
            // Persistir la entidad utilizando el DAO y convertir el resultado a DTO de salida
            return ClienteFrecuenteMapper.entityToViejodto(frecuenteDAO.agregar(frecuente));
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
    public List<ClienteFrecuenteViejoDTO> obtenerClientesPorNombre(String nombre) throws NegocioException {
        // Validar que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }

        // Validar que el nombre solo contenga letras (sin números ni caracteres especiales)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new NegocioException("El nombre solo puede contener letras.");
        }

        try {
            // Obtener la lista de entidades por nombre utilizando el DAO y convertirlas a una lista de DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerPorNombre(nombre));
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
    public List<ClienteFrecuenteViejoDTO> obtenerClientesPorTelefono(String telefono) throws NegocioException {
        // Validar que el teléfono no esté vacío ni nulo
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }

        // Validar que el teléfono contenga solo números
        if (!telefono.matches("\\d+")) {
            throw new NegocioException("El teléfono solo puede contener números.");
        }

        // Validar que el teléfono tenga exactamente 10 dígitos
        if (telefono.length() != 10) {
            throw new NegocioException("El teléfono debe tener exactamente 10 dígitos.");
        }

        try {
            // Obtener la lista de entidades por teléfono utilizando el DAO y convertirlas a una lista de DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerClientePorTelefono(telefono));
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
    public List<ClienteFrecuenteViejoDTO> obtenerClientesPorCorreo(String correo) throws NegocioException {
        // Validar que el correo no esté vacío ni nulo
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacío.");
        }

        // Validar que el correo tenga un formato válido
        String correoRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!correo.matches(correoRegex)) {
            throw new NegocioException("El correo no tiene un formato válido.");
        }

        try {
            // Obtener la lista de entidades por correo utilizando el DAO y convertirlas a una lista de DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerClientePorCorreoElectronico(correo));
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
    public List<ClienteFrecuenteViejoDTO> obtenerTodos() throws NegocioException {
        try {
            // Obtener la lista de entidades utilizando el DAO y convertirlas a una lista de DTOs
            List<ClienteFrecuenteViejoDTO> clientes = ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerTodos());

            // Validar que la lista de clientes no sea nula ni esté vacía
            if (clientes == null || clientes.isEmpty()) {
                throw new NegocioException("No se encontraron clientes.");
            }

            return clientes;
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener todos los clientes", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener todos los clientes", ex);
        }
    }

    /**
     * Método para obtener los clientes por nombre.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param correo Correo del cliente frecuente.
     * @param telefono Teléfono del cliente frecuente.
     * @return Lista de los clientes que su nombre, correo y teléfono coincide
     * con el nombre, correo y teléfono del parámetro.
     * @throws NegocioException Lanza una exception desde la capa de negocio en
     * caso de error.
     */
    @Override
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreCorreoTelefono(String nombre, String correo, String telefono) throws NegocioException {
        // Validar que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }

        // Validar que el nombre solo contenga letras (sin números ni caracteres especiales)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new NegocioException("El nombre solo puede contener letras.");
        }

        // Validar que el correo no sea nulo, vacío y tenga una estructura válida
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacío.");
        }
        // Validar que el correo tenga un formato válido
        String correoRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!correo.matches(correoRegex)) {
            throw new NegocioException("El correo no tiene un formato válido.");
        }

        // Validar que el teléfono no sea nulo, vacío y contenga solo números con longitud de 10
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }
        if (!telefono.matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe contener exactamente 10 dígitos numéricos.");
        }

        try {
            // Obtener la lista de clientes por nombre, correo y teléfono utilizando el DAO y convertirlas a DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerPorNombreCorreoTelefono(nombre, correo, telefono));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por nombre, correo y telefono", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por nombre, correo y telefono", ex);
        }
    }

    /**
     * Método para filtrar los clientes por número de visitas.
     *
     * @param visitas Visitas realizadas por el cliente frecuente.
     * @return Lista de los clientes con un mínimo de visitas. El mínimo de
     * visitas es dado por el parámetro.
     * @throws NegocioException Lanza una exception desde la capa de negocio en
     * caso de error.
     */
    @Override
    public List<ClienteFrecuenteViejoDTO> obtenerPorNumeroVisitas(Integer visitas) throws NegocioException {
        // Validar que el número de visitas no sea nulo
        if (visitas == null) {
            throw new NegocioException("El número de visitas no puede ser nulo.");
        }

        // Validar que el número de visitas sea mayor o igual a 0
        if (visitas < 0) {
            throw new NegocioException("El número de visitas no puede ser negativo.");
        }

        try {
            // Obtener la lista de clientes por número de visitas utilizando el DAO y convertirlas a DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerPorNumeroVisitas(visitas));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por número de visitas", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por número de visitas", ex);
        }
    }

    /**
     * Método para filtrar los clientes por nombre y numero de visitas.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param visitas Visitas realizadas por el cliente frecuente.
     * @return Lista de los clientes filtrados por nombre y con un mínimo de
     * visitas. El mínimo de visitas es dado por el parámetro.
     * @throws NegocioException Lanza una exception desde la capa de negocio en
     * caso de error.
     */
    @Override
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreConVisitas(String nombre, Integer visitas) throws NegocioException {
        // Validar que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }

        // Validar que el nombre solo contenga letras (sin números ni caracteres especiales)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new NegocioException("El nombre solo puede contener letras.");
        }

        // Validar que el número de visitas no sea nulo
        if (visitas == null) {
            throw new NegocioException("El número de visitas no puede ser nulo.");
        }

        // Validar que el número de visitas sea mayor o igual a 0
        if (visitas < 0) {
            throw new NegocioException("El número de visitas no puede ser negativo.");
        }

        try {
            // Obtener la lista de clientes por nombre y número de visitas utilizando el DAO y convertirlas a DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerPorNombreConVisitas(nombre, visitas));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por nombre y visitas", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por nombre y visitas", ex);
        }
    }

    @Override
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreConCorreo(String nombre, String correo) throws NegocioException {
        // Validar que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }

        // Validar que el nombre solo contenga letras (sin números ni caracteres especiales)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new NegocioException("El nombre solo puede contener letras.");
        }

        // Validar que el correo no sea nulo ni vacío
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede ser nulo o vacío.");
        }

        // Validar que el correo tenga un formato válido
        String correoRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!correo.matches(correoRegex)) {
            throw new NegocioException("El correo no tiene un formato válido.");
        }

        try {
            // Obtener la lista de clientes por nombre y correo utilizando el DAO y convertirlas a DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerPorNombreConCorreo(nombre, correo));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por nombre y correo", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por nombre y correo", ex);
        }
    }

    @Override
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreConTelefono(String nombre, String telefono) throws NegocioException {
        // Validar que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }

        // Validar que el nombre solo contenga letras (sin números ni caracteres especiales)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new NegocioException("El nombre solo puede contener letras.");
        }

        // Validar que el teléfono no sea nulo ni vacío
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede ser nulo o vacío.");
        }

        // Validar que el teléfono contenga solo números y tenga exactamente 10 caracteres
        if (!telefono.matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe contener exactamente 10 dígitos numéricos.");
        }

        try {
            // Obtener la lista de clientes por nombre y teléfono utilizando el DAO y convertirlas a DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerPorNombreConTelefono(nombre, telefono));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por nombre y telefono", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por nombre y telefono", ex);
        }
    }

    @Override
    public List<ClienteFrecuenteViejoDTO> obtenerPorCorreoConTelefono(String correo, String telefono) throws NegocioException {
        // Validar que el correo no sea nulo ni vacío
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede ser nulo o vacío.");
        }

        // Validar que el correo tenga un formato válido
        String correoRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!correo.matches(correoRegex)) {
            throw new NegocioException("El correo no tiene un formato válido.");
        }

        // Validar que el teléfono no sea nulo ni vacío
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede ser nulo o vacío.");
        }

        // Validar que el teléfono contenga solo números y tenga exactamente 10 caracteres
        if (!telefono.matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe contener exactamente 10 dígitos numéricos.");
        }

        try {
            // Obtener la lista de clientes por correo y teléfono utilizando el DAO y convertirlas a DTOs
            return ClienteFrecuenteMapper.entityListToViejodtoList(frecuenteDAO.obtenerPorCorreoConTelefono(correo, telefono));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener clientes por correo y telefono", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener clientes por correo y telefono", ex);
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
