/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modulo.comandas;

import conexionBD.Conexion;
import entidades.Cliente;
import entidades.Comanda;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Beto_
 */
@ExtendWith(MockitoExtension.class)
public class ComandaDAOTest {
    
    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private TypedQuery<Comanda> query;

    @InjectMocks
    private ComandaDAO comandaDAO;

    private Comanda comanda1;
    private Comanda comanda2;
    private Cliente cliente1;
    private Cliente cliente2;
    
    
    @BeforeEach
    public void setUp() {
        comanda1 = new Comanda();
        comanda1.setId(1l);
        comanda1.setFolio("OB-20250407-001");
        comanda1.setFechaHora(LocalDateTime.of(2025, 4, 6, 10, 00, 00));
        comanda2 = new Comanda();
        comanda2.setId(2l);
        comanda1.setFolio("OB-20250407-002");
        comanda2.setFechaHora(LocalDateTime.of(2025, 4, 5, 10, 00, 00));
        cliente1 = new Cliente();
        cliente1.setId(8l);
        cliente2 = new Cliente();
        cliente2.setId(12l);
        
        //asignamos el cliente2 ala comanda 1
        comanda1.setCliente(cliente2);
    }

    /**
     * Test of agregar method, of class ComandaDAO.
     */
    @Test
    public void testAgregar() throws PersistenciaException {
        //Mockear la conexion y las transacciones
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            doNothing().when(em).persist(comanda1);
            
            //resultado obtenido
            Comanda result = comandaDAO.agregar(comanda1);
            
            //Verificar los pasos ejecutados
            assertEquals(comanda1, result);
            
            verify(transaction).begin();
            verify(em).persist(comanda1);
            verify(transaction).commit();
            verify(em).close();
        }
    }

    /**
     * Test of eliminar method, of class ComandaDAO.
     */
    @Test
    public void testEliminar() throws PersistenciaException {
        //Mockear la conexion y las transacciones
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            when(em.find(Comanda.class, 1l)).thenReturn(comanda1);
            doNothing().when(em).remove(comanda1);
            
            //resltiado obtenido
            boolean result = comandaDAO.eliminar(1l);

            //verificar los pasos ejecutados
            assertTrue(result);
            verify(transaction).begin();
            verify(em).find(Comanda.class, 1l);
            verify(em).remove(comanda1);
            verify(transaction).commit();
            verify(em).close();
        }
    }

    /**
     * Test of actualizar method, of class ComandaDAO.
     */
    @Test
    public void testActualizar() throws PersistenciaException {
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            when(em.merge(comanda1)).thenReturn(comanda1);

            Comanda result = comandaDAO.actualizar(comanda1);

            assertEquals(comanda1, result);
            verify(transaction).begin();
            verify(em).merge(comanda1);
            verify(transaction).commit();
            verify(em).close();
        }
    }

    /**
     * Test of obtenerPorId method, of class ComandaDAO.
     */
    @Test
    public void testObtenerPorId() throws PersistenciaException {
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.find(Comanda.class, 1l)).thenReturn(comanda1);

            Comanda result = comandaDAO.obtenerPorId(1l);

            assertEquals(comanda1, result);
            verify(em).find(Comanda.class, 1l);
            verify(em).close();
        }
    }
    
    @Test
    void testObtenerPorFolio() throws PersistenciaException{
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            // Simulación de la conexión y ejecución en el DAO
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("Comanda.buscarPorFolio", Comanda.class)).thenReturn(query);
            when(query.setParameter("folio", "OB-20250407-001")).thenReturn(query); //En la simulación la que debe arrojar
            when(query.getSingleResult()).thenReturn(comanda1);

            // Ejecución del método a probar
            Comanda result = comandaDAO.obtenerPorFolio("OB-20250407-001"); //Lo que arroja

            // Verificar resultados
            assertEquals(comanda1, result); // Se comparan ambas

            // Verificar que se llamó a la namedQuery y se cerró la conexión
            verify(em).createNamedQuery("Comanda.buscarPorFolio", Comanda.class);
            verify(query).setParameter("folio", "OB-20250407-001");
            verify(query).getSingleResult();
            verify(em).close();

            // Verificar que no se iniciaron ni commitearon transacciones (es una lectura)
            verify(transaction, never()).begin();
            verify(transaction, never()).commit();
        }
    }
    
    @Test
    void testObtenerPorFechas_rangoValido() throws PersistenciaException {
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("Comanda.buscarPorRangoFechas", Comanda.class)).thenReturn(query);
            LocalDateTime fechaInicio = LocalDateTime.of(2025, 4, 8, 00, 00);
            LocalDateTime fechaFin = LocalDateTime.of(2025, 4, 9, 23, 59);
            when(query.setParameter("fechaInicio", fechaInicio)).thenReturn(query);
            when(query.setParameter("fechaFin", fechaFin)).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(comanda2, comanda1));

            List<Comanda> result = comandaDAO.obtenerPorFechas(fechaInicio, fechaFin);
            
            //Lo que debe haber obtenido y ejecutado
            assertEquals(2, result.size());
            assertEquals(comanda2, result.get(0));
            assertEquals(comanda1, result.get(1));
            verify(em).createNamedQuery("Comanda.buscarPorRangoFechas", Comanda.class);
            verify(query).setParameter("fechaInicio", fechaInicio);
            verify(query).setParameter("fechaFin", fechaFin);
            verify(query).getResultList();
            verify(em).close();
        }
    }

    @Test
    void testObtenerPorFechas_obtenerTodos() throws PersistenciaException {
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.createQuery("SELECT c FROM Comanda c", Comanda.class)).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(comanda1, comanda2));

            List<Comanda> result = comandaDAO.obtenerPorFechas(null, null);
            
            //Lo que debe haber obtenido y ejecutado
            assertEquals(2, result.size());
            assertEquals(comanda1, result.get(0));
            assertEquals(comanda2, result.get(1));
            verify(em).createQuery("SELECT c FROM Comanda c", Comanda.class);
            verify(query).getResultList();
            verify(em).close();
        }
    }
    

    /**
     * Test of obtenerTodos method, of class ComandaDAO.
     */
    @Test
    public void testObtenerTodos() throws PersistenciaException {
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            List<Comanda> comandas = new ArrayList<>();
            comandas.add(comanda1);
            comandas.add(comanda2);
            when(em.createQuery("SELECT c FROM Comanda c", Comanda.class)).thenReturn(query);
            when(query.getResultList()).thenReturn(comandas);

            List<Comanda> result = comandaDAO.obtenerTodos();

            assertEquals(comandas, result);
            verify(em).createQuery("SELECT c FROM Comanda c", Comanda.class);
            verify(query).getResultList();
            verify(em).close();
        }
    }

    /**
     * Test of asignarCliente method, of class ComandaDAO.
     */
    @Test
    public void testAsignarCliente() throws PersistenciaException {
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            when(em.find(Comanda.class, 1l)).thenReturn(comanda1);
            when(em.find(Cliente.class, 12l)).thenReturn(cliente1); //Aqupi va a encontrar el id12
            when(em.merge(comanda1)).thenReturn(comanda1);
            when(em.merge(cliente2)).thenReturn(cliente2); //Stub para el cliente anterior y el nuevo

            boolean result = comandaDAO.asignarCliente(1l, 12l);

            assertTrue(result);
            verify(transaction).begin();
            verify(em).find(Comanda.class, 1l);
            verify(em).find(Cliente.class, 12l);
            verify(em).merge(comanda1);
            verify(em).merge(cliente2);
            verify(transaction).commit();
            verify(em).close();
        }
    }

    /**
     * Test of desasignarCliente method, of class ComandaDAO.
     */
    @Test
    public void testDesasignarCliente() throws PersistenciaException {
        try (MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)) {
                conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
                when(em.getTransaction()).thenReturn(transaction);
                when(em.find(Comanda.class, 1l)).thenReturn(comanda1);
                when(em.merge(comanda1)).thenReturn(comanda1);
                when(em.merge(cliente2)).thenReturn(cliente2);

                boolean result = comandaDAO.desasignarCliente(1l);

                assertTrue(result);
                assertNull(comanda1.getCliente());
                verify(transaction).begin();
                verify(em).find(Comanda.class, 1l);
                verify(em).merge(comanda1);
                verify(em).merge(cliente2);
                verify(transaction).commit();
                verify(em).close();
        }
    }
}
