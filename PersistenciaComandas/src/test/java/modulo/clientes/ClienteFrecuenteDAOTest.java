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

/**
 *
 * @author brand
 */
@ExtendWith(MockitoExtension.class)
//Con esta etiqueta se habilitan las extensiones 
// de mockito, usando mocks para las pruebas
public class ClienteFrecuenteDAOTest {

    @Mock //Representan los objetos simulados
    //No interactuan con la bd de adeveras
    private EntityManager em;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private TypedQuery<ClienteFrecuente> query;

    @InjectMocks //Crea la instancia de la clase de prueba
    //intecyando en automatico los mocks de abajo
    private ClienteFrecuenteDAO frecuenteDAO;

    private ClienteFrecuente frecuente1;
    private ClienteFrecuente frecuente2;
    private List<ClienteFrecuente> frecuentes;

    @BeforeEach //Este metodo (Antes de cada uno)
    // Se ejecuta antes de cada prueba
    //Son como maniquies de prueba
    void setUp() {
        frecuente1 = new ClienteFrecuente();
        frecuente1.setId(1L);
        frecuente1.setNombres("Ramon Valdez");
        frecuente1.setTelefono("111111111");
        frecuente1.setCorreo("ramon@gmail.com");

        frecuente2 = new ClienteFrecuente();
        frecuente2.setId(2L);
        frecuente2.setNombres("Maria GV");
        frecuente2.setTelefono("000000000");
        frecuente2.setCorreo("mariagv@gmail.com");

        frecuentes = Arrays.asList(frecuente1, frecuente2);
    }

    public ClienteFrecuenteDAOTest() {
    }

    /**
     * Test of agregar method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testAgregar() throws Exception {

        //Dentro de cada test se utiliza un mockito para SIMULAR la clase
        // estática de la conexión con la bd
        //Se utiliza try with resourses
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {

            //Si se crea la conexión entonces regresa el entityManager
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);

            //Cuando se crea la transacción, regresa la conexión
            when(em.getTransaction()).thenReturn(transaction);

            //Se asigna el resultado
            ClienteFrecuente result = frecuenteDAO.agregar(frecuente1);

            //Si el resultado de la acción es igual al resultado esperado
            assertEquals(frecuente1, result);

            //Se verifica que los metodos hayan sido llamados con
            // el ingrediente de prueba
            verify(em).persist(frecuente1);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    /**
     * Test of obtenerClientePorCorreoElectrónico method, of class
     * ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerClientePorCorreoElectrónico() throws Exception {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorCorreo", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("correo", "%omat%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente1));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerClientePorCorreoElectronico("maria");

            assertEquals(1, result.size());
            assertEquals("mariagv", result.get(1).getTelefono());
            verify(em).createNamedQuery("Ingrediente.buscarPorTelefono", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerClientePorTelefono method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerClientePorTelefono() throws Exception {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorTelefono", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("nombre", "%omat%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente1));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerClientePorTelefono("11111");

            assertEquals(1, result.size());
            assertEquals("1111111111", result.get(0).getTelefono());
            verify(em).createNamedQuery("Ingrediente.buscarPorTelefono", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorNombre method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerPorNombre() throws Exception {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ClienteFrecuente.buscarPorNombre", ClienteFrecuente.class)).thenReturn(query);
            when(query.setParameter("nombre", "%omat%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(frecuente1));

            List<ClienteFrecuente> result = frecuenteDAO.obtenerPorNombre("ari");

            assertEquals(1, result.size());
            assertEquals("Maria GV", result.get(1).getNombres());
            verify(em).createNamedQuery("Ingrediente.buscarPorNombre", ClienteFrecuente.class);
            verify(em).close();
        }
    }

    /**
     * Test of obtenerTodos method, of class ClienteFrecuenteDAO.
     */
    @Test
    public void testObtenerTodos() throws Exception {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createQuery("SELECT i FROM Ingrediente i", ClienteFrecuente.class)).thenReturn(query);
            when(query.getResultList()).thenReturn(frecuentes);

            List<ClienteFrecuente> result = frecuenteDAO.obtenerTodos();

            assertEquals(2, result.size());
            assertEquals("Ramon Valdez", result.get(0).getNombres());
            assertEquals("Maria GV", result.get(1).getNombres());
            verify(em).createQuery("SELECT cf FROM ClientesFrecuentes cf", ClienteFrecuente.class);
            verify(em).close();
        }
    }

}
