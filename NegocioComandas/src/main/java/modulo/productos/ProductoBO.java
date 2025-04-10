/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.productos;

import DTOs.nuevos.ProductoNuevoDTO;
import DTOs.viejos.ProductoViejoDTO;
import entidades.Producto;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.IngredienteMapper;
import mappers.ProductoMapper;

/**
 *
 * @author Maximiliano
 */
public class ProductoBO implements IProductoBO
{
    ProductoMapper productoMapper = new ProductoMapper();

    private IProductoDAO productoDAO;
    private static final Logger LOGGER = Logger.getLogger(ProductoBO.class.getName());

    /**
     * Constructor que recibe una instancia de DAO por inyección.
     * 
     * @param productoDAO DAO de acceso a datos de productos
     */
    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    /**
     * Agrega un nuevo producto al sistema, validando reglas de negocio.
     * 
     * @param productoNuevo DTO con los datos del nuevo producto
     * @return ProductoViejoDTO con los datos del producto agregado
     * @throws NegocioException si ocurre un error de negocio o persistencia
     */
    @Override
    public ProductoViejoDTO agregarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException {
        validarProductoDTO(productoNuevo);
        validarNombreUnico(productoNuevo.getNombre());

        Producto producto = ProductoMapper.toEntity(productoNuevo);

        try {
            Producto agregado = productoDAO.agregar(producto);
            return ProductoMapper.toViejoDTO(agregado);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al agregar producto", ex);
            throw new NegocioException("Error al agregar producto", ex);
        }
    }

    /**
     * Modifica un producto existente en el sistema.
     * 
     * @param productoNuevo DTO con los nuevos datos del producto
     * @return ProductoViejoDTO actualizado
     * @throws NegocioException si ocurre un error de validación o persistencia
     */
    @Override
    public ProductoViejoDTO modificarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException {
        validarProductoDTO(productoNuevo);

        Producto producto = ProductoMapper.toEntity(productoNuevo);

        try {
            Producto actualizado = productoDAO.actualizar(producto);
            return ProductoMapper.toViejoDTO(actualizado);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al actualizar producto", ex);
            throw new NegocioException("Error al actualizar producto", ex);
        }
    }

    /**
     * Elimina (deshabilita) un producto por su ID.
     * 
     * @param id ID del producto
     * @return true si fue deshabilitado con éxito
     * @throws NegocioException si ocurre un error de persistencia
     */
    @Override
    public boolean deshabilitarProducto(Long id) throws NegocioException {
        try {
            return productoDAO.deshabilitar(id);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al deshabilitar producto", ex);
            throw new NegocioException("Error al deshabilitar producto", ex);
        }
    }

    /**
     * Obtiene un producto por su ID.
     * 
     * @param id ID del producto
     * @return DTO del producto encontrado
     * @throws NegocioException si ocurre un error o el producto no existe
     */
    @Override
    public ProductoViejoDTO obtenerProductoPorId(Long id) throws NegocioException {
        try {
            return ProductoMapper.toViejoDTO(productoDAO.obtenerPorId(id));
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener producto por ID", ex);
            throw new NegocioException("Error al obtener producto por ID", ex);
        }
    }

    /**
     * Obtiene todos los productos activos.
     * 
     * @return Lista de productos activos en forma de DTOs
     * @throws NegocioException si ocurre un error al obtener los productos
     */
    @Override
    public List<ProductoViejoDTO> obtenerTodos() throws NegocioException {
        try {
            return ProductoMapper.toViejoDTOList(productoDAO.obtenerTodos());
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener todos los productos", ex);
            throw new NegocioException("Error al obtener todos los productos", ex);
        }
    }

    /**
     * Busca productos por coincidencia parcial de nombre.
     * 
     * @param nombre nombre parcial a buscar
     * @return lista de productos que coincidan
     * @throws NegocioException si ocurre un error
     */
    @Override
    public List<ProductoViejoDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            return ProductoMapper.toViejoDTOList(productoDAO.buscarPorNombre(nombre));
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al buscar productos por nombre", ex);
            throw new NegocioException("Error al buscar productos por nombre", ex);
        }
    }

    /**
     * Busca productos por tipo o nombre parcial.
     * 
     * @param filtro texto a filtrar por nombre o tipo
     * @return lista de productos filtrados
     * @throws NegocioException si ocurre un error
     */
    @Override
    public List<ProductoViejoDTO> buscarPorTipo(String filtro) throws NegocioException {
        try {
            return ProductoMapper.toViejoDTOList(productoDAO.buscarPorNombreOCategoria(filtro));
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al buscar productos por categoría", ex);
            throw new NegocioException("Error al buscar productos por categoría", ex);
        }
    }

    /**
     * Valida que el DTO del producto no sea nulo y tenga valores válidos.
     */
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

    /**
     * Verifica que no exista otro producto con el mismo nombre (ignorando mayúsculas).
     */
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
