package modulo.clientes;

import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * IClienteFrecuenteDAO.java
 *
 * Interfaz para ClienteFrecuenteDAO con los métodos para agregar y obtener
 * clientes frecuentes
 *
 * @author brand
 */
public interface IClienteFrecuenteDAO {

    /**
     * Método para agregar un cliente frecuente
     *
     * @param clienteFrecuente Cliente Frecuente
     * @return Cliente frecuente que se agregó
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public ClienteFrecuente agregar(ClienteFrecuente clienteFrecuente) throws PersistenciaException;

    /**
     * }
     * Método para obtener clientes por correo electrónico
     *
     * @param correo Correo electrónico
     * @return Lista de los clientes que su correo coincide con el correo del
     * parámetro
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerClientePorCorreoElectronico(String correo) throws PersistenciaException;

    /**
     * Método para obtener clientes por telefono
     *
     * @param telefono Teléfono
     * @return Lista de los clientes que su telefono coincide con el teléfono
     * del parámetro
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerClientePorTelefono(String telefono) throws PersistenciaException;

    /**
     * Método para obtener los clientes por nombre
     *
     * @param nombre Nombre del cliente frecuente
     * @return Lista de los clientes que su nombre coincide con el nombre del
     * parámetro
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerPorNombre(String nombre) throws PersistenciaException;
    
    /**
     * Método para obtener los clientes por nombre.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param correo Correo del cliente frecuente.
     * @return Lista de los clientes que su nombre y correo coincide con el
     * nombre y correo del parámetro.
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerPorNombreConCorreo(String nombre, String correo) throws PersistenciaException;
    
    /**
     * Método para obtener los clientes por nombre.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param telefono Teléfono del cliente frecuente.
     * @return Lista de los clientes que su nombre y teléfono coincide con el
     * nombre y teléfono del parámetro.
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerPorNombreConTelefono(String nombre, String telefono) throws PersistenciaException;
    
    /**
     * Método para obtener los clientes por nombre.
     *
     * @param correo Correo del cliente frecuente.
     * @param telefono Teléfono del cliente frecuente.
     * @return Lista de los clientes que su correo y teléfono coincide con el
     * correo y teléfono del parámetro.
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerPorCorreoConTelefono(String correo, String telefono) throws PersistenciaException;
    
    /**
     * Método para obtener los clientes por nombre.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param correo Correo del cliente frecuente.
     * @param telefono Teléfono del cliente frecuente.
     * @return Lista de los clientes que su nombre, correo y teléfono coincide
     * con el nombre, correo y teléfono del parámetro.
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerPorNombreCorreoTelefono(String nombre, String correo, String telefono) throws PersistenciaException;
    
     /**
     * Método para filtrar los clientes por número de visitas.
     *
     * @param visitas Visitas realizadas por el cliente frecuente.
     * @return Lista de los clientes con un mínimo de visitas. El mínimo de
     * visitas es dado por el parámetro.
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerPorNumeroVisitas(Integer visitas) throws PersistenciaException;
    
    /**
     * Método para filtrar los clientes por nombre y numero de visitas.
     *
     * @param nombre Nombre del cliente frecuente.
     * @param visitas Visitas realizadas por el cliente frecuente.
     * @return Lista de los clientes filtrados por nombre y con un mínimo de
     * visitas. El mínimo de visitas es dado por el parámetro.
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerPorNombreConVisitas(String nombre, Integer visitas) throws PersistenciaException;

    /**
     * Método para obtener todos los clientes
     *
     * @return Lista de todos los clientes registrados
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerTodos() throws PersistenciaException;
}
