package modulo.clientes;

import DTOs.nuevos.ClientesFrecuentesDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author brand
 */
public interface IClienteFrecuenteBO {

    /**
     * Agrega un nuevo cliente frecuente al sistema tras validar la información.
     *
     * @param frecuente DTO que contiene la información del nuevo cliente.
     * @return Un ClientesFrecuentesDTO que representa el cliente agregado.
     * @throws NegocioException Si se violan reglas de negocio o hay un error de
     * persistencia.
     */
    public ClientesFrecuentesDTO agregarClienteFrecuente(ClientesFrecuentesDTO frecuente) throws NegocioException;

    /**
     * Obtiene una lista de clientes frecuentes cuyo nombre coincide con el
     * proporcionado.
     *
     * @param nombre Nombre del cliente a buscar.
     * @return Lista de clientes con el nombre especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClientesFrecuentesDTO> obtenerClientesPorNombre(String nombre) throws NegocioException;

    /**
     * Obtiene una lista de clientes frecuentes cuyo teléfono coincide con el
     * proporcionado.
     *
     * @param telefono Número de teléfono del cliente a buscar.
     * @return Lista de clientes con el teléfono especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClientesFrecuentesDTO> obtenerClientesPorTelefono(String telefono) throws NegocioException;

    /**
     * Obtiene una lista de clientes frecuentes cuyo correo electrónico coincide
     * con el proporcionado.
     *
     * @param correo Correo electrónico del cliente a buscar.
     * @return Lista de clientes con el correo especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClientesFrecuentesDTO> obtenerClientesPorCorreo(String correo) throws NegocioException;

    /**
     * Obtiene la lista completa de clientes frecuentes registrados en el sistema.
     *
     * @return Lista de todos los clientes frecuentes.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClientesFrecuentesDTO> obtenerTodos() throws NegocioException;
}
