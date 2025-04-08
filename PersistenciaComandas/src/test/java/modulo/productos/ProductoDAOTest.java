/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modulo.productos;

import conexionBD.Conexion;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
 * @author Maximiliano
 */
@ExtendWith(MockitoExtension.class)
public class ProductoDAOTest {
    
    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private TypedQuery<Producto> query;
    
    @Mock
    private TypedQuery<Producto> queryMock;

    @InjectMocks
    private ProductoDAO productoDAO;

    private Producto producto1;
    private Producto producto2;
    private List<Producto> productos;

    @BeforeEach
    void setUp() {
        producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Hamburguesa");
        producto1.setEstado(true);


        producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Papas Fritas");
        producto2.setEstado(true);
        
        productos = Arrays.asList(producto1, producto2);
    }

    @Test
    void testAgregar() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);

            Producto result = productoDAO.agregar(producto1);

            assertEquals(producto1, result);
            verify(em).persist(producto1);
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
            when(em.merge(producto1)).thenReturn(producto1);

            Producto result = productoDAO.actualizar(producto1);

            assertEquals(producto1, result);
            verify(em).merge(producto1);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    @Test
    void testDeshabilitar() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            when(em.find(Producto.class, 1L)).thenReturn(producto1);

            boolean result = productoDAO.deshabilitar(1L);

            assertTrue(result);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    @Test
    void testObtenerPorId() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.find(Producto.class, 1L)).thenReturn(producto1);

            Producto result = productoDAO.obtenerPorId(1L);

            assertEquals(producto1, result);
            verify(em).find(Producto.class, 1L);
            verify(em).close();
        }
    }

    /*@Test
    void testObtenerTodos() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createQuery("SELECT p FROM Producto p WHERE p.estado = true", Producto.class)).thenReturn(queryMock);
            when(query.getResultList()).thenReturn(productos);

            List<Producto> result = productoDAO.obtenerTodos();

            assertEquals(2, result.size());
            assertEquals("Hamburguesa", result.get(0).getNombre());
            assertEquals("Papas Fritas", result.get(1).getNombre());
            verify(em).createQuery("SELECT p FROM Producto p", Producto.class);
            verify(em).close();
        }
    }*/
    
    /*@Test
void testObtenerTodos() throws PersistenciaException {
    // Arrange
    List<Producto> productos = Arrays.asList(new Producto(), new Producto());
    when(em.createQuery("SELECT p FROM Producto p WHERE p.estado = true", Producto.class))
        .thenReturn(queryMock);
    when(queryMock.getResultList()).thenReturn(productos);

    // Act
    List<Producto> resultado = productoDAO.obtenerTodos();

    // Assert
    assertEquals(productos, resultado);
    verify(em).createQuery("SELECT p FROM Producto p WHERE p.estado = true", Producto.class);
    verify(queryMock).getResultList();
}*/
    
    @Test
void testObtenerTodos() throws PersistenciaException {
    try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
        // 👇 Faltaba esto
        mockedConexion.when(Conexion::crearConexion).thenReturn(em);

        // Arrange
        List<Producto> productos = Arrays.asList(new Producto(), new Producto());
        when(em.createQuery("SELECT p FROM Producto p WHERE p.estado = true", Producto.class))
            .thenReturn(query);
        when(query.getResultList()).thenReturn(productos);

        // Act
        List<Producto> resultado = productoDAO.obtenerTodos();

        // Assert
        assertEquals(productos, resultado);
        verify(em).createQuery("SELECT p FROM Producto p WHERE p.estado = true", Producto.class);
        verify(query).getResultList();
        verify(em).close(); // opcional si tu DAO cierra la conexión
    }
}
    
    @Test
    void testBuscarPorNombre() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("Producto.buscarPorNombre", Producto.class)).thenReturn(query);
            when(query.setParameter("nombre", "%urguesa%")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(producto1));

            List<Producto> result = productoDAO.buscarPorNombre("urguesa");

            assertEquals(1, result.size());
            assertEquals("Hamburguesa", result.get(0).getNombre());
            verify(em).createNamedQuery("Producto.buscarPorNombre", Producto.class);
            verify(em).close();
        }
    }

    @Test
    void testBuscarPorNombreOCategoria() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("Producto.buscarPorNombreOCategoria", Producto.class)).thenReturn(query);
            when(query.setParameter("filtro", "urguesa")).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(producto1));

            List<Producto> result = productoDAO.buscarPorNombreOCategoria("urguesa");

            assertEquals(1, result.size());
            assertEquals("Hamburguesa", result.get(0).getNombre());
            verify(em).createNamedQuery("Producto.buscarPorNombreOCategoria", Producto.class);
            verify(em).close();
        }
    }
    
}
