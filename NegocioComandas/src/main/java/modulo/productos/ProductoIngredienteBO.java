/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.productos;

import DTOs.nuevos.ProductoIngredienteNuevoDTO;
import DTOs.viejos.ProductoIngredienteViejoDTO;
import entidades.Ingrediente;
import entidades.Producto;
import entidades.ProductoIngrediente;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Maximiliano
 */
public class ProductoIngredienteBO implements IProductoIngredienteBO
{
    private IProductoIngredienteDAO productoIngredienteDAO;
    private static final Logger LOGGER = Logger.getLogger(ProductoIngredienteBO.class.getName());

    public ProductoIngredienteBO(IProductoIngredienteDAO productoIngredienteDAO) {
        this.productoIngredienteDAO = productoIngredienteDAO;
    }

    @Override
    public ProductoIngredienteViejoDTO agregarRelacion(ProductoIngredienteNuevoDTO dto) throws NegocioException {
        validarDTO(dto);

        try {
            Producto producto = obtenerProductoPorId(dto.getIdProducto());
            Ingrediente ingrediente = obtenerIngredientePorId(dto.getIdIngrediente());

            ProductoIngrediente entidad = new ProductoIngrediente(
                    dto.getCantidadRequerida(),
                    producto,
                    ingrediente
            );

            ProductoIngrediente resultado = productoIngredienteDAO.agregar(entidad);

            return new ProductoIngredienteViejoDTO(
                    resultado.getId(),
                    resultado.getCantidadRequerida(),
                    resultado.getProducto().getId(),
                    resultado.getIngrediente().getId()
            );
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
            List<ProductoIngredienteViejoDTO> dtos = new ArrayList<>();

            for (ProductoIngrediente pi : relaciones) {
                dtos.add(new ProductoIngredienteViejoDTO(
                        pi.getId(),
                        pi.getCantidadRequerida(),
                        pi.getProducto().getId(),
                        pi.getIngrediente().getId()
                ));
            }

            return dtos;
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

    private Producto obtenerProductoPorId(Long idProducto) throws NegocioException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
            EntityManager em = emf.createEntityManager();
            Producto producto = em.find(Producto.class, idProducto);
            em.close();
            if (producto == null) {
                throw new NegocioException("Producto no encontrado con ID: " + idProducto);
            }
            return producto;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener producto", ex);
            throw new NegocioException("Error al obtener producto con ID: " + idProducto, ex);
        }
    }

    private Ingrediente obtenerIngredientePorId(Long idIngrediente) throws NegocioException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
            EntityManager em = emf.createEntityManager();
            Ingrediente ingrediente = em.find(Ingrediente.class, idIngrediente);
            em.close();
            if (ingrediente == null) {
                throw new NegocioException("Ingrediente no encontrado con ID: " + idIngrediente);
            }
            return ingrediente;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener ingrediente", ex);
            throw new NegocioException("Error al obtener ingrediente con ID: " + idIngrediente, ex);
        }
    }

}
