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
     * Método para obtener todos los clientes
     *
     * @return Lista de todos los clientes registrados
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    public List<ClienteFrecuente> obtenerTodos() throws PersistenciaException;
}
