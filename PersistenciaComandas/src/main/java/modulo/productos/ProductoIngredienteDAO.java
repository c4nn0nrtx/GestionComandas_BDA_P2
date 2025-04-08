/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.productos;

import conexionBD.Conexion;
import entidades.ProductoIngrediente;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maximiliano
 */
public class ProductoIngredienteDAO implements IProductoIngredienteDAO
{
    private static ProductoIngredienteDAO instance;

    private ProductoIngredienteDAO() {
    }

    public static ProductoIngredienteDAO getInstance() {
        if (instance == null) {
            instance = new ProductoIngredienteDAO();
        }
        return instance;
    }

    @Override
    public ProductoIngrediente agregar(ProductoIngrediente productoIngrediente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(productoIngrediente);
            em.getTransaction().commit();
            return productoIngrediente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al agregar relación producto-ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            ProductoIngrediente pi = em.find(ProductoIngrediente.class, id);
            if (pi != null) {
                em.remove(pi);
                em.getTransaction().commit();
                return true;
            } else {
                throw new PersistenciaException("No se encontró la relación con ID: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar relación producto-ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductoIngrediente> obtenerPorProducto(Long idProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ProductoIngrediente> query = em.createNamedQuery("ProductoIngrediente.buscarPorProducto", ProductoIngrediente.class);
            query.setParameter("idProducto", idProducto);
            return query.getResultList();
        } catch (NoResultException e) {
            throw new PersistenciaException("No se encontraron ingredientes para el producto con ID: " + idProducto);
        } finally {
            em.close();
        }
    }
}
