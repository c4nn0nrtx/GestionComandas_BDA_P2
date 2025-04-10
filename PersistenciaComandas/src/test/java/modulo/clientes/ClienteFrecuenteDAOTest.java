/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modulo.clientes;

import conexionBD.Conexion;
import entidades.ClienteFrecuente;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import excepciones.PersistenciaException;

/**
 *
 * @author brand
 */
@ExtendWith(MockitoExtension.class)
public class ClienteFrecuenteDAOTest {

    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private TypedQuery<ClienteFrecuente> query;

    @InjectMocks
    private ClienteFrecuenteDAO frecuenteDAO;

    private ClienteFrecuente frecuente1;
    private ClienteFrecuente frecuente2;
    private List<ClienteFrecuente> frecuentes;

    @BeforeEach
    void setUp() {
        frecuente1 = new ClienteFrecuente();
        frecuente1.setId(1L);
        frecuente1.setNombres("Ramon Valdez");
        frecuente1.setTelefono("111111111");
        frecuente1.setCorreo("ramon@gmail.com");
        frecuente1.setVisitas(139);

        frecuente2 = new ClienteFrecuente();
        frecuente2.setId(2L);
        frecuente2.setNombres("Maria GV");
        frecuente2.setTelefono("000000000");
        frecuente2.setCorreo("mariagv@gmail.com");
        frecuente2.setVisitas(831);

        frecuentes = Arrays.asList(frecuente1, frecuente2);
    }

    public ClienteFrecuenteDAOTest() {
    }

    /**
     * Test of agregar method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testAgregar() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);

            ClienteFrecuente result = frecuenteDAO.agregar(frecuente1);

            assertEquals(frecuente1, result);
            verify(em).persist(frecuente1);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    /**
     * Test of obtenerClientePorCorreoElectronico method, of class
     * ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerClientePorCorreoElectronico() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorCorreo", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("correo", "%maria%"))
                    .thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente2));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerClientePorCorreoElectronico("maria");

            assertEquals(1, result.size());
            assertEquals("000000000", result.get(0).getTelefono());
            verify(em).createNamedQuery("ClienteFrecuente.buscarPorCorreo", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerClientePorTelefono method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerClientePorTelefono() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorTelefono", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("telefono", "%11111%"))
                    .thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente1));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerClientePorTelefono("11111");

            assertEquals(1, result.size());
            assertEquals("111111111", result.get(0).getTelefono());
            verify(em).createNamedQuery("ClienteFrecuente.buscarPorTelefono", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorNombre method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerPorNombre() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorNombre", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("nombre", "%Maria%"))
                    .thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente2));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorNombre("Maria");

            assertEquals(1, result.size());
            assertEquals("Maria GV", result.get(0).getNombres());
            verify(em).createNamedQuery("ClienteFrecuente.buscarPorNombre", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerTodos method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerTodos() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createQuery("SELECT cf FROM ClienteFrecuente cf", ClienteFrecuente.class)).thenReturn(query);
            when(query.getResultList()).thenReturn(frecuentes);

            List<ClienteFrecuente> result = frecuenteDAO.obtenerTodos();

            assertEquals(2, result.size());
            assertEquals("Ramon Valdez", result.get(0).getNombres());
            assertEquals("Maria GV", result.get(1).getNombres());
            verify(em).createQuery("SELECT cf FROM ClienteFrecuente cf", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorNombreConCorreo method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerPorNombreConCorreo() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorNombreCorreo", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("nombre", "%Maria%")).thenReturn(query);
            when(query.setParameter("correo", "%mariagv@gmail.com%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente2));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorNombreConCorreo("Maria", "mariagv@gmail.com");

            assertEquals(1, result.size());
            assertEquals("Maria GV", result.get(0).getNombres());
            assertEquals("mariagv@gmail.com", result.get(0).getCorreo());
            verify(em).createNamedQuery("ClienteFrecuente.buscarPorNombreCorreo", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorNombreConTelefono method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerPorNombreConTelefono() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorNombreTelefono", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("nombre", "%Maria%")).thenReturn(query);
            when(query.setParameter("telefono", "%000000000%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente2));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorNombreConTelefono("Maria", "000000000");

            assertEquals(1, result.size());
            assertEquals("Maria GV", result.get(0).getNombres());
            assertEquals("000000000", result.get(0).getTelefono());
            verify(em).createNamedQuery("ClienteFrecuente.buscarPorNombreTelefono", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    @Test
    public void testObtenerPorCorreoConTelefono() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorCorreoTelefono", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("correo", "%mariagv@gmail.com%")).thenReturn(query);
            when(query.setParameter("telefono", "%000000000%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente2));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorCorreoConTelefono("mariagv@gmail.com", "000000000");

            assertEquals(1, result.size());
            assertEquals("Maria GV", result.get(0).getNombres());
            assertEquals("000000000", result.get(0).getTelefono());
            assertEquals("mariagv@gmail.com", result.get(0).getCorreo());
            verify(em).createNamedQuery("ClienteFrecuente.buscarPorCorreoTelefono", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorNombreCorreoTelefono method, of class
     * ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerPorNombreCorreoTelefono() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            // Mock de la conexión a la base de datos
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);

            // Configuración del EntityManager y la consulta
            when(em.createNamedQuery("ClienteFrecuente.buscarPorNombreCorreoTelefono", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("nombre", "%Maria%")).thenReturn(query);
            when(query.setParameter("correo", "%mariagv@gmail.com%")).thenReturn(query);
            when(query.setParameter("telefono", "%000000000%")).thenReturn(query);

            // Simulación del resultado de la consulta
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente2));

            // Llamada al método que se está probando
            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorNombreCorreoTelefono("Maria", "mariagv@gmail.com", "000000000");

            // Verificación de los resultados
            assertEquals(1, result.size());
            assertEquals("Maria GV", result.get(0).getNombres());
            assertEquals("mariagv@gmail.com", result.get(0).getCorreo());
            assertEquals("000000000", result.get(0).getTelefono());

            // Verificación de las interacciones con el EntityManager
            verify(em).createNamedQuery("ClienteFrecuente.buscarPorNombreCorreoTelefono", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorNumeroVisitas method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerPorNumeroVisitas() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createQuery("SELECT cf FROM ClienteFrecuente cf WHERE cf.numeroVisitas >= :visitas", ClienteFrecuente.class))
                    .thenReturn(query);
            when(query.setParameter("visitas", 100)).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente1, frecuente2)); // Ambos clientes tienen visitas >= 100

            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorNumeroVisitas(100);

            assertEquals(2, result.size());
            assertTrue(result.contains(frecuente1));
            assertTrue(result.contains(frecuente2));
            verify(em).createQuery("SELECT cf FROM ClienteFrecuente cf WHERE cf.numeroVisitas >= :visitas", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorNombreConVisitas method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerPorNombreConVisitas() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createQuery("SELECT cf FROM ClienteFrecuente cf WHERE cf.nombre LIKE :nombre AND cf.numeroVisitas >= :visitas", ClienteFrecuente.class))
                    .thenReturn(query);
            when(query.setParameter("nombre", "%Maria%")).thenReturn(query);
            when(query.setParameter("visitas", 500)).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente2)); // Solo Maria tiene visitas >= 500

            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorNombreConVisitas("Maria", 500);

            assertEquals(1, result.size());
            assertEquals("Maria GV", result.get(0).getNombres());
            verify(em).createQuery("SELECT cf FROM ClienteFrecuente cf WHERE cf.nombre LIKE :nombre AND cf.numeroVisitas >= :visitas", ClienteFrecuente.class);
            verify(em).close();
        }
    }

}
