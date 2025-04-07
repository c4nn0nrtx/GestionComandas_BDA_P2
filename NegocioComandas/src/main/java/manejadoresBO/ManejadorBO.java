/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadoresBO;

import modulo.DetalleComandaBO;
import modulo.IDetalleComandaBO;
import modulo.IIngredienteBO;
import modulo.IProductoBO;
import modulo.IProductoIngredienteBO;
import modulo.IngredienteBO;
import modulo.ProductoBO;
import modulo.ProductoIngredienteBO;
import modulo.productos.DetalleComandaDAO;
import modulo.productos.IDetalleComandaDAO;
import modulo.productos.IIngredienteDAO;
import modulo.productos.IProductoDAO;
import modulo.productos.IProductoIngredienteDAO;
import modulo.productos.IngredienteDAO;
import modulo.productos.ProductoDAO;
import modulo.productos.ProductoIngredienteDAO;

/**
 *
 * @author Maximiliano
 */
public class ManejadorBO 
{
    public static IProductoBO crearProductoBO() {
        IProductoDAO productoDAO = ProductoDAO.getInstance();
        return new ProductoBO(productoDAO);
    }

    public static IDetalleComandaBO crearDetalleComandaBO() {
        IDetalleComandaDAO detalleComandaDAO = DetalleComandaDAO.getInstance();
        return new DetalleComandaBO(detalleComandaDAO);
    }

    public static IProductoIngredienteBO crearProductoIngredienteBO() {
        IProductoIngredienteDAO productoIngredienteDAO = ProductoIngredienteDAO.getInstance();
        return new ProductoIngredienteBO(productoIngredienteDAO);
    }
}
