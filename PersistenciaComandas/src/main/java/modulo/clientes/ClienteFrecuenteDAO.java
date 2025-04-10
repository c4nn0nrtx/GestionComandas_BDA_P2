package modulo.clientes;

import conexionBD.Conexion;
import entidades.ClienteFrecuente;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * ClienteFrecuenteDAO.java
 *
 * Clase DAO de cliente frecuente con los métodos para agregar un cliente y
 * obtener clientes
 *
 * @author brand
 */
public class ClienteFrecuenteDAO implements IClienteFrecuenteDAO {

    private static ClienteFrecuenteDAO instanceClienteFrecuenteDAO;

    private ClienteFrecuenteDAO() {
    }

    /**
     *
     * @return
     */
    public static ClienteFrecuenteDAO getInstanceDAO() {
        if (instanceClienteFrecuenteDAO == null) {
            instanceClienteFrecuenteDAO = new ClienteFrecuenteDAO();
        }
        return instanceClienteFrecuenteDAO;
    }

    /**
     * Método para agregar un cliente frecuente
     *
     * @param clienteFrecuente Cliente Frecuente
     * @return Cliente frecuente que se agregó
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    @Override
    public ClienteFrecuente agregar(ClienteFrecuente clienteFrecuente) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();

        try {
            //1. Iniciamos la transacción
            em.getTransaction().begin();

            //2. Persistimos
            em.persist(clienteFrecuente);

            //3. Finalizamos la transacción
            em.getTransaction().commit();
            return clienteFrecuente;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();

            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al guardar el cliente: " + e.getMessage());
        } finally {
            //fin. Independiente del resultado, se cierra el entityManager
            em.close();
        }
    }

    /**
     * Método para obtener clientes por correo electrónico
     *
     * @param correo Correo electrónico
     * @return Lista de los clientes que su correo coincide con el correo del
     * parámetro
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    @Override
    public List<ClienteFrecuente> obtenerClientePorCorreoElectronico(String correo) throws PersistenciaException {

        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("ClienteFrecuente.buscarPorCorreo", ClienteFrecuente.class);

            // 2. Añadimos parametros
            consulta.setParameter("correo", "%" + correo + "%");

            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();

        } catch (NoResultException e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    /**
     * Método para obtener clientes por telefono
     *
     * @param telefono Teléfono
     * @return Lista de los clientes que su telefono coincide con el teléfono
     * del parámetro
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    @Override
    public List<ClienteFrecuente> obtenerClientePorTelefono(String telefono) throws PersistenciaException {

        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("ClienteFrecuente.buscarPorTelefono", ClienteFrecuente.class);

            // 2. Añadimos parametros
            consulta.setParameter("telefono", "%" + telefono + "%");

            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();

        } catch (NoResultException e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    /**
     * Método para obtener los clientes por nombre
     *
     * @param nombre Nombre del cliente frecuente
     * @return Lista de los clientes que su nombre coincide con el nombre del
     * parámetro
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    @Override
    public List<ClienteFrecuente> obtenerPorNombre(String nombre) throws PersistenciaException {

        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("ClienteFrecuente.buscarPorNombre", ClienteFrecuente.class);

            // 2. Añadimos parametros
            consulta.setParameter("nombre", "%" + nombre + "%");

            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();

        } catch (NoResultException e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    /**
     * Método para obtener todos los clientes
     *
     * @return Lista de todos los clientes registrados
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    @Override
    public List<ClienteFrecuente> obtenerTodos() throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        try {
            //1. retornamos el resultado obtenido
            return em.createQuery("SELECT cf FROM ClienteFrecuente cf", ClienteFrecuente.class).getResultList();
        } catch (Exception e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al obtener todos los clientes frecuentes: " + e.getMessage());
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

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
    @Override
    public List<ClienteFrecuente> obtenerPorNombreConCorreo(String nombre, String correo) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("ClienteFrecuente.buscarPorNombreCorreo", ClienteFrecuente.class);

            // 2. Añadimos parametros
            consulta.setParameter("nombre", "%" + nombre + "%");
            consulta.setParameter("correo", "%" + correo + "%");

            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();

        } catch (NoResultException e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

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
    @Override
    public List<ClienteFrecuente> obtenerPorNombreConTelefono(String nombre, String telefono) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("ClienteFrecuente.buscarPorNombreTelefono", ClienteFrecuente.class);

            // 2. Añadimos parametros
            consulta.setParameter("nombre", "%" + nombre + "%");
            consulta.setParameter("telefono", "%" + telefono + "%");

            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();

        } catch (NoResultException e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

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
    @Override
    public List<ClienteFrecuente> obtenerPorCorreoConTelefono(String correo, String telefono) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("ClienteFrecuente.buscarPorCorreoTelefono", ClienteFrecuente.class);

            // 2. Añadimos parametros
            consulta.setParameter("correo", "%" + correo + "%");
            consulta.setParameter("telefono", "%" + telefono + "%");

            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();

        } catch (NoResultException e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        } finally {
            //fin. Cerramos el entityManager
            em.close();
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
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    @Override
    public List<ClienteFrecuente> obtenerPorNombreCorreoTelefono(String nombre, String correo, String telefono) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("ClienteFrecuente.buscarPorNombreCorreoTelefono", ClienteFrecuente.class);

            // 2. Añadimos parametros
            consulta.setParameter("nombre", "%" + nombre + "%");
            consulta.setParameter("correo", "%" + correo + "%");
            consulta.setParameter("telefono", "%" + telefono + "%");

            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();

        } catch (NoResultException e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    /**
     * Método para filtrar los clientes por número de visitas.
     *
     * @param visitas Visitas realizadas por el cliente frecuente.
     * @return Lista de los clientes con un mínimo de visitas. El mínimo de
     * visitas es dado por el parámetro.
     * @throws PersistenciaException Lanza una exception desde la capa de
     * persistencia en caso de error.
     */
    @Override
    public List<ClienteFrecuente> obtenerPorNumeroVisitas(Integer visitas) throws PersistenciaException {
        // 0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery<ClienteFrecuente> consulta = em.createQuery(
                    "SELECT cf FROM ClienteFrecuente cf WHERE cf.numeroVisitas >= :visitas", ClienteFrecuente.class);

            // 2. Añadimos parámetros
            consulta.setParameter("visitas", visitas);

            // 3. Retornamos el resultado
            return consulta.getResultList();

        } catch (Exception e) {
            // ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al obtener los clientes por número de visitas: " + e.getMessage());
        } finally {
            // fin. Cerramos el entityManager
            em.close();
        }
    }

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
    @Override
    public List<ClienteFrecuente> obtenerPorNombreConVisitas(String nombre, Integer visitas) throws PersistenciaException {
        // 0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        try {
            // 1. Creamos la consulta
            TypedQuery<ClienteFrecuente> consulta = em.createQuery(
                    "SELECT cf FROM ClienteFrecuente cf WHERE cf.nombre LIKE :nombre AND cf.numeroVisitas >= :visitas",
                    ClienteFrecuente.class);

            // 2. Añadimos parámetros
            consulta.setParameter("nombre", "%" + nombre + "%");
            consulta.setParameter("visitas", visitas);

            // 3. Retornamos el resultado
            return consulta.getResultList();

        } catch (Exception e) {
            // ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al obtener los clientes por nombre y visitas: " + e.getMessage());
        } finally {
            // fin. Cerramos el entityManager
            em.close();
        }
    }
}