/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modulo.productos;

import conexionBD.Conexion;
import entidades.ProductoIngrediente;
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
public class ProductoIngredienteDAOTest {
    
    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private TypedQuery<ProductoIngrediente> query;

    @InjectMocks
    private ProductoIngredienteDAO dao;

    private ProductoIngrediente productoIngrediente;

    @BeforeEach
    void setUp() {
        productoIngrediente = new ProductoIngrediente();
        productoIngrediente.setId(1L);
        productoIngrediente.setCantidadRequerida(5.0);
    }

    @Test
    void testAgregar() throws PersistenciaException {
        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);

            ProductoIngrediente result = dao.agregar(productoIngrediente);

            assertEquals(productoIngrediente, result);
            verify(em).persist(productoIngrediente);
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
            when(em.find(ProductoIngrediente.class, 1L)).thenReturn(productoIngrediente);

            boolean result = dao.eliminar(1L);

            assertTrue(result);
            verify(em).remove(productoIngrediente);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
        }
    }

    @Test
    void testObtenerPorProducto() throws PersistenciaException {
        Long idProducto = 10L;
        List<ProductoIngrediente> expectedList = Arrays.asList(productoIngrediente);

        try (MockedStatic<Conexion> mockedConexion = Mockito.mockStatic(Conexion.class)) {
            mockedConexion.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("ProductoIngrediente.buscarPorProducto", ProductoIngrediente.class))
                .thenReturn(query);
            when(query.setParameter("idProducto", idProducto)).thenReturn(query);
            when(query.getResultList()).thenReturn(expectedList);

            List<ProductoIngrediente> result = dao.obtenerPorProducto(idProducto);

            assertEquals(expectedList, result);
            verify(query).setParameter("idProducto", idProducto);
            verify(query).getResultList();
            verify(em).close();
        }
    }
    
    
    
}
