/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.productos;

import DTOs.nuevos.ProductoNuevoDTO;
import DTOs.viejos.ProductoViejoDTO;
import ENUMs.TipoProducto;
import entidades.Producto;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        // Validación: el DTO no debe ser nulo
    if (productoNuevo == null) {
        throw new NegocioException("El producto no puede ser nulo.");
    }

    // Validación: nombre no debe ser nulo o vacío
    if (productoNuevo.getNombre() == null || productoNuevo.getNombre().trim().isEmpty()) {
        throw new NegocioException("El nombre del producto no puede estar vacío.");
    }

    // Validación: nombre solo puede contener letras, espacios y algunos caracteres válidos
    if (!productoNuevo.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ0-9\\s]+")) {
        throw new NegocioException("El nombre del producto contiene caracteres inválidos.");
    }

    // Validación: tipo de producto no debe ser nulo
    if (productoNuevo.getTipo() == null) {
        throw new NegocioException("El tipo de producto no puede ser nulo.");
    }

    // Validación: precio no debe ser nulo ni menor o igual a cero
    if (productoNuevo.getPrecio() <= 0) {
        throw new NegocioException("El precio del producto debe ser mayor a cero.");
    }

    // Validación: estado debe ser true (solo se permite registrar productos activos)
    if (!productoNuevo.isEstado()) {
        throw new NegocioException("Solo se pueden registrar productos habilitados.");
    }

    // Validación: que no exista otro producto con el mismo nombre (ignorando mayúsculas)
    try {
        List<Producto> productosExistentes = productoDAO.obtenerTodos();
        for (Producto p : productosExistentes) {
            if (p.getNombre().equalsIgnoreCase(productoNuevo.getNombre())) {
                throw new NegocioException("Ya existe un producto registrado con ese nombre.");
            }
        }
    } catch (PersistenciaException ex) {
        LOGGER.log(Level.SEVERE, "Error al validar nombre único", ex);
        throw new NegocioException("Error al validar nombre único", ex);
    }

    // Mapeo y persistencia
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
        // Validación: el DTO no debe ser nulo
    if (productoNuevo == null) {
        throw new NegocioException("El producto no puede ser nulo.");
    }

    // Validación: nombre no debe ser nulo o vacío
    if (productoNuevo.getNombre() == null || productoNuevo.getNombre().trim().isEmpty()) {
        throw new NegocioException("El nombre del producto no puede estar vacío.");
    }

    // Validación: nombre solo puede contener letras, números y espacios
    if (!productoNuevo.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ0-9\\s]+")) {
        throw new NegocioException("El nombre del producto contiene caracteres inválidos.");
    }

    // Validación: tipo de producto no debe ser nulo
    if (productoNuevo.getTipo() == null) {
        throw new NegocioException("El tipo de producto no puede ser nulo.");
    }

    // Validación: precio debe ser mayor a cero
    if (productoNuevo.getPrecio() <= 0) {
        throw new NegocioException("El precio del producto debe ser mayor a cero.");
    }

    // Validación: estado debe estar activo para permitir modificación
    if (!productoNuevo.isEstado()) {
        throw new NegocioException("No se puede modificar un producto deshabilitado.");
    }

    // Validar que el producto exista en la base de datos
    Producto productoExistente;
    try {
        productoExistente = productoDAO.obtenerPorId(
            buscarIdPorNombre(productoNuevo.getNombre())
        );
    } catch (PersistenciaException ex) {
        LOGGER.log(Level.SEVERE, "Error al verificar existencia del producto", ex);
        throw new NegocioException("No se encontró el producto a modificar.", ex);
    }

    // Validar que no haya otro producto con el mismo nombre (caso duplicado)
    try {
        List<Producto> productos = productoDAO.obtenerTodos();
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(productoNuevo.getNombre())
                && !p.getId().equals(productoExistente.getId())) {
                throw new NegocioException("Ya existe otro producto con ese nombre.");
            }
        }
    } catch (PersistenciaException ex) {
        LOGGER.log(Level.SEVERE, "Error al validar nombre único", ex);
        throw new NegocioException("Error al validar nombre único", ex);
    }

    // Mapeo a entidad
    Producto producto = ProductoMapper.toEntity(productoNuevo);
    producto.setId(productoExistente.getId()); // importante mantener el ID original

    // Actualización
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
        // Validación: ID no puede ser nulo
    if (id == null) {
        throw new NegocioException("El ID del producto no puede ser nulo.");
    }

    // Validación: ID debe ser mayor que 0
    if (id <= 0) {
        throw new NegocioException("El ID del producto debe ser un número positivo.");
    }

    // Validación: el producto debe existir y estar activo
    Producto producto;
    try {
        producto = productoDAO.obtenerPorId(id);
    } catch (PersistenciaException ex) {
        LOGGER.log(Level.SEVERE, "Error al obtener el producto por ID para deshabilitar", ex);
        throw new NegocioException("No se pudo encontrar el producto con ID: " + id, ex);
    }

    // Validación: si el producto ya está deshabilitado, no se puede volver a deshabilitar
    if (!producto.isEstado()) {
        throw new NegocioException("El producto con ID " + id + " ya está deshabilitado.");
    }

    // Deshabilitación
    try {
        return productoDAO.deshabilitar(id);
    } catch (PersistenciaException ex) {
        LOGGER.log(Level.SEVERE, "Error al deshabilitar producto", ex);
        throw new NegocioException("Error al deshabilitar producto con ID: " + id, ex);
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
        // Validación: ID no puede ser nulo
    if (id == null) {
        throw new NegocioException("El ID del producto no puede ser nulo.");
    }

    // Validación: ID debe ser mayor que 0
    if (id <= 0) {
        throw new NegocioException("El ID del producto debe ser un número positivo.");
    }

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
        List<ProductoViejoDTO> productos = ProductoMapper.toViejoDTOList(productoDAO.obtenerTodos());

        if (productos == null || productos.isEmpty()) {
            throw new NegocioException("No hay productos activos registrados.");
        }

        return productos;
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
        if (nombre == null || nombre.trim().isEmpty()) {
        throw new NegocioException("El nombre de búsqueda no puede estar vacío.");
    }

    try {
        List<ProductoViejoDTO> resultados = ProductoMapper.toViejoDTOList(productoDAO.buscarPorNombre(nombre));
        
        if (resultados == null || resultados.isEmpty()) {
            throw new NegocioException("No se encontraron productos con ese nombre.");
        }

        return resultados;
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
        if (filtro == null || filtro.trim().isEmpty()) {
        throw new NegocioException("El filtro de búsqueda no puede estar vacío.");
    }

    try {
        List<ProductoViejoDTO> resultados = ProductoMapper.toViejoDTOList(productoDAO.buscarPorNombreOCategoria(filtro));

        if (resultados == null || resultados.isEmpty()) {
            throw new NegocioException("No se encontraron productos con ese filtro.");
        }

        return resultados;
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
    
    private Long buscarIdPorNombre(String nombre) throws PersistenciaException {
    List<Producto> productos = productoDAO.obtenerTodos();
    for (Producto p : productos) {
        if (p.getNombre().equalsIgnoreCase(nombre)) {
            return p.getId();
        }
    }
    throw new PersistenciaException("Producto no encontrado por nombre.");
    }

    @Override
    public List<ProductoViejoDTO> obtenerPorTipo(TipoProducto tipoProducto) throws NegocioException {
        try {
            // Obtener la lista de entidades por unidad de medida utilizando el DAO y convertirlas a una lista de DTOs
            return ProductoMapper.toViejoDTOList(productoDAO.obtenerPorTipo(tipoProducto));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener productos por tipo de producto", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener productos por tipo de producto", ex);
        }
    }
    
    @Override
    public List<ProductoViejoDTO> obtenerPorFiltro(String nombre, TipoProducto tipo) throws NegocioException {
        try {
            // Obtener la lista de entidades por unidad de medida y/o nombre utilizando el DAO y convertirlas a una lista de DTOs
            return ProductoMapper.toViejoDTOList(productoDAO.obtenerPorFiltro(nombre, tipo));
        } catch (PersistenciaException ex) {
            // Loggear el error de persistencia
            LOGGER.log(Level.SEVERE, "Error al obtener productos por tipo de producto", ex);
            // Lanzar una excepción de negocio
            throw new NegocioException("Error al obtener productos por tipo de producto", ex);
        }
    }

}
