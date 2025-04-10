/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.productos;

import DTOs.nuevos.ProductoIngredienteNuevoDTO;
import DTOs.viejos.ProductoIngredienteViejoDTO;
import entidades.ProductoIngrediente;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.ProductoIngredienteMapper;

/**
 *
 * @author Maximiliano
 */
public class ProductoIngredienteBO implements IProductoIngredienteBO
{
    ProductoIngredienteMapper productoIngredienteMapper = new ProductoIngredienteMapper();
    
    private final IProductoIngredienteDAO productoIngredienteDAO;

    private static final Logger LOGGER = Logger.getLogger(ProductoIngredienteBO.class.getName());

    public ProductoIngredienteBO(IProductoIngredienteDAO productoIngredienteDAO) {
        this.productoIngredienteDAO = productoIngredienteDAO;
    }

    @Override
    public ProductoIngredienteViejoDTO agregarRelacion(ProductoIngredienteNuevoDTO dto) throws NegocioException {
        validarDTO(dto);
        try {
            ProductoIngrediente entidad = ProductoIngredienteMapper.toEntity(dto);
            ProductoIngrediente guardado = productoIngredienteDAO.agregar(entidad);
            return ProductoIngredienteMapper.toViejoDTO(guardado);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al agregar relación producto-ingrediente", ex);
            throw new NegocioException("Error al agregar relación producto-ingrediente", ex);
        }
    }

    @Override
    public boolean eliminarRelacion(Long id) throws NegocioException {
        try {
            return productoIngredienteDAO.eliminar(id);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al eliminar relación producto-ingrediente", ex);
            throw new NegocioException("Error al eliminar relación producto-ingrediente", ex);
        }
    }

    @Override
    public List<ProductoIngredienteViejoDTO> obtenerPorProducto(Long idProducto) throws NegocioException {
        try {
            List<ProductoIngrediente> relaciones = productoIngredienteDAO.obtenerPorProducto(idProducto);
            return ProductoIngredienteMapper.toViejoDTOList(relaciones);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener relaciones por producto", ex);
            throw new NegocioException("Error al obtener relaciones por producto", ex);
        }
    }

    private void validarDTO(ProductoIngredienteNuevoDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("El DTO no puede ser nulo.");
        }
        if (dto.getCantidadRequerida() <= 0) {
            throw new NegocioException("La cantidad requerida debe ser mayor a cero.");
        }
        if (dto.getIdProducto() == null || dto.getIdIngrediente() == null) {
            throw new NegocioException("ID de producto e ingrediente no pueden ser nulos.");
        }
    }

}
