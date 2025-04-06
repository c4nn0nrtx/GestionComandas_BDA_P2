/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.ingredientes;

import ENUMs.TipoProducto;
import entidades.Producto;
import entidades.ProductoIngrediente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Maximiliano
 */
public class ProductoDAO implements IProductoDAO
{
    private EntityManagerFactory emf;

    public ProductoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void agregarProducto(Producto producto) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            boolean existe = existeProductoConNombre(producto.getNombre());
            if (existe) {
                throw new Exception("Ya existe un producto con ese nombre.");
            }

            em.persist(producto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarProducto(Long idProducto) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, idProducto);

            if (producto != null) {
                em.remove(producto);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Producto buscarPorId(Long idProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Producto.class, idProducto);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String nombreParcial) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Producto.buscarPorNombreParcial", Producto.class)
                     .setParameter("nombre", nombreParcial)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarPorTipo(TipoProducto tipo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Producto.buscarPorTipo", Producto.class)
                     .setParameter("tipo", tipo)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeProductoConNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createNamedQuery("Producto.existeNombre", Long.class)
                           .setParameter("nombre", nombre)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean validarDisponibilidadIngredientes(Long idProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            Producto producto = em.find(Producto.class, idProducto);
            if (producto == null) return false;

            for (ProductoIngrediente pi : producto.getProductosIngredientes()) {
                if (pi.getIngrediente().getStock() < pi.getCantidadRequerida()) {
                    return false;
                }
            }
            return true;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> obtenerProductosActivos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Producto.todosActivos", Producto.class).getResultList();
        } finally {
            em.close();
        }
    }

}
