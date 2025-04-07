/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modulo.productos;

import entidades.Producto;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Maximiliano
 */
public class ProductoDAOTest {
    
    public ProductoDAOTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ProductoDAO expResult = null;
        ProductoDAO result = ProductoDAO.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregar method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testAgregar() throws Exception {
        System.out.println("agregar");
        Producto producto = null;
        ProductoDAO instance = null;
        Producto expResult = null;
        Producto result = instance.agregar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizar method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testActualizar() throws Exception {
        System.out.println("actualizar");
        Producto producto = null;
        ProductoDAO instance = null;
        Producto expResult = null;
        Producto result = instance.actualizar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deshabilitar method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testDeshabilitar() throws Exception {
        System.out.println("deshabilitar");
        Long id = null;
        ProductoDAO instance = null;
        boolean expResult = false;
        boolean result = instance.deshabilitar(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPorId method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testObtenerPorId() throws Exception {
        System.out.println("obtenerPorId");
        Long id = null;
        ProductoDAO instance = null;
        Producto expResult = null;
        Producto result = instance.obtenerPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTodos method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testObtenerTodos() throws Exception {
        System.out.println("obtenerTodos");
        ProductoDAO instance = null;
        List<Producto> expResult = null;
        List<Producto> result = instance.obtenerTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorNombre method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testBuscarPorNombre() throws Exception {
        System.out.println("buscarPorNombre");
        String nombre = "";
        ProductoDAO instance = null;
        List<Producto> expResult = null;
        List<Producto> result = instance.buscarPorNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorCategoria method, of class ProductoDAO.
     */
    @org.junit.jupiter.api.Test
    public void testBuscarPorCategoria() throws Exception {
        System.out.println("buscarPorCategoria");
        String categoria = "";
        ProductoDAO instance = null;
        List<Producto> expResult = null;
        List<Producto> result = instance.buscarPorCategoria(categoria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
