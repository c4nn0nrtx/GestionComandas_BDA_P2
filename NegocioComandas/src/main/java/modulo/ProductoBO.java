/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo;

import DTOs.nuevos.ProductoNuevoDTO;
import DTOs.viejos.ProductoViejoDTO;
import entidades.Producto;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.ProductoMapper;
import modulo.productos.IProductoDAO;
import modulo.productos.ProductoDAO;

/**
 *
 * @author Maximiliano
 */
public class ProductoBO implements IProductoBO
{
    private IProductoDAO productoDAO;
    private static final Logger LOGGER = Logger.getLogger(ProductoBO.class.getName());

    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public Producto agregarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException {
        validarProductoDTO(productoNuevo);
        validarNombreUnico(productoNuevo.getNombre());

        Producto producto = ProductoMapper.toEntity(productoNuevo);

        try {
            return productoDAO.agregar(producto);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al agregar producto", ex);
            throw new NegocioException("Error al agregar producto", ex);
        }
    }

    @Override
    public boolean deshabilitarProducto(Long id) throws NegocioException {
        try {
            return productoDAO.deshabilitar(id);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al deshabilitar producto", ex);
            throw new NegocioException("Error al deshabilitar producto", ex);
        }
    }

    @Override
    public Producto modificarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException {
        validarProductoDTO(productoNuevo);

        Producto producto = ProductoMapper.toEntity(productoNuevo);

        try {
            return productoDAO.actualizar(producto);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al actualizar producto", ex);
            throw new NegocioException("Error al actualizar producto", ex);
        }
    }

    @Override
    public Producto obtenerProductoPorId(Long id) throws NegocioException {
        try {
            return productoDAO.obtenerPorId(id);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener producto por ID", ex);
            throw new NegocioException("Error al obtener producto por ID", ex);
        }
    }

    @Override
    public List<ProductoViejoDTO> obtenerTodos() throws NegocioException {
        try {
            return ProductoMapper.toViejoDTOList(productoDAO.obtenerTodos());
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener todos los productos", ex);
            throw new NegocioException("Error al obtener todos los productos", ex);
        }
    }

    @Override
    public List<ProductoViejoDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            return ProductoMapper.toViejoDTOList(productoDAO.buscarPorNombre(nombre));
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al buscar productos por nombre", ex);
            throw new NegocioException("Error al buscar productos por nombre", ex);
        }
    }

    @Override
    public List<ProductoViejoDTO> buscarPorTipo(String tipo) throws NegocioException {
        try {
            return ProductoMapper.toViejoDTOList(productoDAO.buscarPorCategoria(tipo));
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al buscar productos por categoría", ex);
            throw new NegocioException("Error al buscar productos por categoría", ex);
        }
    }

    private void validarProductoDTO(ProductoNuevoDTO producto) throws NegocioException {
        if (producto == null) {
            throw new NegocioException("El producto no puede ser nulo.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }
        if (producto.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a cero.");
        }
        if (producto.getTipo() == null) {
        throw new NegocioException("La categoría del producto no puede estar vacía.");
        }
    }

    private void validarNombreUnico(String nombre) throws NegocioException {
        try {
            List<Producto> productos = productoDAO.obtenerTodos();
            for (Producto p : productos) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    throw new NegocioException("Ya existe un producto con ese nombre.");
                }
            }
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al validar nombre único", ex);
            throw new NegocioException("Error al validar nombre único", ex);
        }
    }

}
