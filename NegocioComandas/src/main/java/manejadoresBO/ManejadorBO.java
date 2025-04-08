/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadoresBO;

import modulo.ingredientes.IIngredienteBO;
import modulo.ingredientes.IIngredienteDAO;
import modulo.ingredientes.IngredienteBO;
import modulo.ingredientes.IngredienteDAO;
import modulo.productos.DetalleComandaBO;
import modulo.productos.DetalleComandaDAO;
import modulo.productos.IDetalleComandaBO;
import modulo.productos.IDetalleComandaDAO;
import modulo.productos.IProductoBO;
import modulo.productos.IProductoDAO;
import modulo.productos.IProductoIngredienteBO;
import modulo.productos.IProductoIngredienteDAO;
import modulo.productos.ProductoBO;
import modulo.productos.ProductoDAO;
import modulo.productos.ProductoIngredienteBO;
import modulo.productos.ProductoIngredienteDAO;

/**
 *
 * @author Beto_
 */
public class ManejadorBO {
    public static IIngredienteBO crearIngredienteBO() {
        // 1. Creamos la instancia del DAO a utilizar.
        // Se obtiene la única instancia disponible debido al uso del patrón Singleton.
        IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();

        // 2. Creamos la instancia del BO, inyectando el DAO como dependencia.
        IIngredienteBO ingredienteBO = new IngredienteBO(ingredienteDAO);

        // 3. Retornamos la instancia del BO lista para ser utilizada.
        return ingredienteBO;
    }
    
     public static IProductoBO crearProductoBO() {
        IProductoDAO productoDAO = ProductoDAO.getInstance();
        IProductoBO productoBO = new ProductoBO(productoDAO);
        return productoBO;
    }

    public static IDetalleComandaBO crearDetalleComandaBO() {
        IDetalleComandaDAO detalleComandaDAO = DetalleComandaDAO.getInstance();
        IDetalleComandaBO detalleComandaBO = new DetalleComandaBO(detalleComandaDAO);
        return detalleComandaBO;
    }

    public static IProductoIngredienteBO crearProductoIngredienteBO() {
        IProductoIngredienteDAO productoIngredienteDAO = ProductoIngredienteDAO.getInstance();
        IProductoIngredienteBO productoIngredienteBO = new ProductoIngredienteBO(productoIngredienteDAO);
        return productoIngredienteBO;
    }
    
}
