package modulo.clientes;

import DTOs.nuevos.ClienteFrecuenteNuevoDTO;
import DTOs.viejos.ClienteFrecuenteViejoDTO;
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
     * @return Un ClienteFrecuenteViejoDTO que representa el cliente agregado.
     * @throws NegocioException Si se violan reglas de negocio o hay un error de
     * persistencia.
     */
    public ClienteFrecuenteViejoDTO agregarClienteFrecuente(ClienteFrecuenteNuevoDTO frecuente) throws NegocioException;

    /**
     * Obtiene una lista de clientes frecuentes cuyo nombre coincide con el
     * proporcionado.
     *
     * @param nombre Nombre del cliente a buscar.
     * @return Lista de clientes con el nombre especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerClientesPorNombre(String nombre) throws NegocioException;

    /**
     * Obtiene una lista de clientes frecuentes cuyo teléfono coincide con el
     * proporcionado.
     *
     * @param telefono Número de teléfono del cliente a buscar.
     * @return Lista de clientes con el teléfono especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerClientesPorTelefono(String telefono) throws NegocioException;

    /**
     * Obtiene una lista de clientes frecuentes cuyo correo electrónico coincide
     * con el proporcionado.
     *
     * @param correo Correo electrónico del cliente a buscar.
     * @return Lista de clientes con el correo especificado.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerClientesPorCorreo(String correo) throws NegocioException;

    /**
     * Método para obtener los clientes por nombre.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param correo Correo del cliente frecuente.
     * @return Lista de los clientes que su nombre y correo coincide con el
     * nombre y correo del parámetro.
     * @throws NegocioException Lanza una exception desde la capa de negocio en
     * caso de error.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreConCorreo(String nombre, String correo) throws NegocioException;

    /**
     * Método para obtener los clientes por nombre.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param telefono Teléfono del cliente frecuente.
     * @return Lista de los clientes que su nombre y teléfono coincide con el
     * nombre y teléfono del parámetro.
     * @throws NegocioException Lanza una exception desde la capa de negocio en
     * caso de error.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreConTelefono(String nombre, String telefono) throws NegocioException;

    /**
     * Método para obtener los clientes por nombre.
     *
     * @param correo Correo del cliente frecuente.
     * @param telefono Teléfono del cliente frecuente.
     * @return Lista de los clientes que su correo y teléfono coincide con el
     * correo y teléfono del parámetro.
     * @throws NegocioException Lanza una exception desde la capa de negocio en
     * caso de error.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerPorCorreoConTelefono(String correo, String telefono) throws NegocioException;

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
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreCorreoTelefono(String nombre, String correo, String telefono) throws NegocioException;

    /**
     * Método para filtrar los clientes por número de visitas.
     *
     * @param visitas Visitas realizadas por el cliente frecuente.
     * @return Lista de los clientes con un mínimo de visitas. El mínimo de
     * visitas es dado por el parámetro.
     * @throws NegocioException Lanza una exception desde la capa de negocio en
     * caso de error.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerPorNumeroVisitas(Integer visitas) throws NegocioException;

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
    public List<ClienteFrecuenteViejoDTO> obtenerPorNombreConVisitas(String nombre, Integer visitas) throws NegocioException;

    /**
     * Obtiene la lista completa de clientes frecuentes registrados en el
     * sistema.
     *
     * @return Lista de todos los clientes frecuentes.
     * @throws NegocioException Si ocurre un error en la consulta.
     */
    public List<ClienteFrecuenteViejoDTO> obtenerTodos() throws NegocioException;
}
