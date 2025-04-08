/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modulo.ingredientes;

import ENUMs.UnidadMedida;
import conexionBD.Conexion;
import entidades.Ingrediente;
import excepciones.PersistenciaException;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
 * @author Beto_
 */
@ExtendWith(MockitoExtension.class)
//Con esta etiqueta se habilitan las extensiones 
// de mockito, usando mocks para las pruebas
public class IngredienteDAOTest {

    @Mock //Representan los objetos simulados
    //No interactuan con la bd de adeveras
    private EntityManager em;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private TypedQuery<Ingrediente> query;

    @InjectMocks //Crea la instancia de la clase de prueba
    //intecyando en automatico los mocks de abajo
    private IngredienteDAO ingredienteDAO;

    private Ingrediente ingrediente1;
    private Ingrediente ingrediente2;
    private List<Ingrediente> ingredientes;

    @BeforeEach //Este metodo (Antes de cada uno)
    // Se ejecuta antes de cada prueba
    //Son como maniquies de prueba
    void setUp() {
        ingrediente1 = new Ingrediente();
        ingrediente1.setId(1L);
        ingrediente1.setNombre("Tomate");

        ingrediente2 = new Ingrediente();
        ingrediente2.setId(2L);
        ingrediente2.setNombre("Cebolla");

        ingredientes = Arrays.asList(ingrediente1, ingrediente2);
    }

    @Test //Indica una prueba individual
    void testAgregar() throws PersistenciaException {
        
        //Dentro de cada test se utiliza un mockito para SIMULAR la clase
        // estática de la conexión con la bd
        //Se utiliza try with resourses
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            
            //Si se crea la conexión entonces regresa el entityManager
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            
            //Cuando se crea la transacción, regresa la conexión
            when(em.getTransaction()).thenReturn(transaction);
            
            //Se asigna el resultado
            Ingrediente result = ingredienteDAO.agregar(ingrediente1);
            
            //Si el resultado de la acción es igual al resultado esperado
            assertEquals(ingrediente1, result);
            
            //Se verifica que los metodos hayan sido llamados con
            // el ingrediente de prueba
            verify(em).persist(ingrediente1);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    @Test
    void testEliminar() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            when(em.find(Ingrediente.class, 1L)).thenReturn(ingrediente1);

            boolean result = ingredienteDAO.eliminar(1L);

            assertTrue(result); //assert pero con boolean
            verify(em).remove(ingrediente1);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    @Test
    void testActualizar() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            when(em.merge(ingrediente1)).thenReturn(ingrediente1);

            Ingrediente result = ingredienteDAO.actualizar(ingrediente1);

            assertEquals(ingrediente1, result);
            verify(em).merge(ingrediente1);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    @Test
    void testObtenerPorId() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.find(Ingrediente.class, 1L)).thenReturn(ingrediente1);

            Ingrediente result = ingredienteDAO.obtenerPorId(1L);

            assertEquals(ingrediente1, result);
            verify(em).find(Ingrediente.class, 1L);
            verify(em).close();
        }
    }

    @Test
    void testObtenerTodos() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class)).thenReturn(query);
            when(query.getResultList()).thenReturn(ingredientes);

            List<Ingrediente> result = ingredienteDAO.obtenerTodos();

            assertEquals(2, result.size());
            assertEquals("Tomate", result.get(0).getNombre());
            assertEquals("Cebolla", result.get(1).getNombre());
            verify(em).createQuery("SELECT i FROM Ingrediente i", Ingrediente.class);
            verify(em).close();
        }
    }

    @Test
    void testObtenerPorNombre() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("Ingrediente.buscarPorNombre", Ingrediente.class)).thenReturn(query);
            when(query.setParameter("nombre", "%omat%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(ingrediente1));

            List<Ingrediente> result = ingredienteDAO.obtenerPorNombre("omat");

            assertEquals(1, result.size());
            assertEquals("Tomate", result.get(0).getNombre());
            verify(em).createNamedQuery("Ingrediente.buscarPorNombre", Ingrediente.class);
            verify(em).close();
        }
    }

    @Test
    void testObtenerPorUnidadMedida() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("Ingrediente.buscarPorUnidadMedida", Ingrediente.class)).thenReturn(query);
            when(query.setParameter("unidadMedida", UnidadMedida.GRAMOS)).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(ingrediente1));

            List<Ingrediente> result = ingredienteDAO.obtenerPorUnidadMedida(UnidadMedida.GRAMOS);

            assertEquals(1, result.size());
            assertEquals("Tomate", result.get(0).getNombre());
            verify(em).createNamedQuery("Ingrediente.buscarPorUnidadMedida", Ingrediente.class);
            verify(em).close();
        }
    }
}
