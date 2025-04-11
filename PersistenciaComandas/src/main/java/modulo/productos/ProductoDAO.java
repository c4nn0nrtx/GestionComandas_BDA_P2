/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.productos;

import ENUMs.TipoProducto;
import conexionBD.Conexion;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maximiliano
 */
public class ProductoDAO implements IProductoDAO 
{
    private static ProductoDAO instance;

    private ProductoDAO() {
    
    }

    public static ProductoDAO getInstance() {
        if (instance == null) {
            instance = new ProductoDAO();
        }
        return instance;
    }

    @Override
    public Producto agregar(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
            return producto;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al agregar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Producto actualizar(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Producto actualizado = em.merge(producto);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deshabilitar(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                producto.setEstado(false);
                em.merge(producto);
                em.getTransaction().commit();
                return true;
            } else {
                throw new PersistenciaException("No se encontró el producto con ID: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al deshabilitar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
@Override
public boolean habilitar(Long id) throws PersistenciaException {
    EntityManager em = Conexion.crearConexion();
    try {
        em.getTransaction().begin();
        Producto producto = em.find(Producto.class, id);
        if (producto != null) {
            producto.setEstado(true); // Cambia el estado a habilitado
            em.merge(producto);
            em.getTransaction().commit();
            return true;
        } else {
            throw new PersistenciaException("No se encontró el producto con ID: " + id);
        }
    } catch (Exception e) {
        em.getTransaction().rollback();
        throw new PersistenciaException("Error al habilitar el producto: " + e.getMessage());
    } finally {
        em.close();
    }
}

    @Override
    public Producto obtenerPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto != null && producto.isEstado()) {
                return producto;
            } else {
                throw new PersistenciaException("Producto no encontrado o deshabilitado.");
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> obtenerTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.estado = true", Producto.class)
                     .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener productos: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Producto> query = em.createNamedQuery("Producto.buscarPorNombre", Producto.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            throw new PersistenciaException("No se encontraron productos con ese nombre.");
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Producto> buscarPorNombreOCategoria(String filtro) throws PersistenciaException {
       EntityManager em = Conexion.crearConexion();
       try {
        TypedQuery<Producto> query = em.createNamedQuery("Producto.buscarPorNombreOCategoria", Producto.class);
        query.setParameter("filtro", filtro);
        return query.getResultList();
        } catch (NoResultException e) {
        throw new PersistenciaException("No se encontraron productos que coincidan con el filtro.");
        } finally {
          em.close();
        }
    }
    
    @Override
    public List<Producto> obtenerPorTipo(TipoProducto tipo) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try{            
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("Producto.buscarPorTipo", Producto.class);
            
            // 2. Añadimos parametros
            consulta.setParameter("tipo", tipo);
            
            // 3. validamos resultado de la consulta y devolvemos
            return consulta.getResultList();
            
        }catch(NoResultException e){
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        }finally{            
            //fin. Cerramos el entityManager
            em.close();
        }
    }
    
    @Override
    public List<Producto> obtenerPorFiltro(String nombre, TipoProducto tipo) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try{
            // x. Si el nombre y la unidad de medidas no son nulos, se aplican ambos filtros
            if (nombre != null && !nombre.trim().isEmpty() && tipo != null && !tipo.toString().trim().isEmpty()) {
                TypedQuery<Producto> query = em.createQuery(
                        "SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.tipo = :tipo",
                        Producto.class);
                query.setParameter("nombre", "%" + nombre + "%");
                query.setParameter("tipo", tipo);
                return query.getResultList();
                
                //Si solo el nombre no esta vacío
            } else if (nombre != null && !nombre.trim().isEmpty()) {
                TypedQuery<Producto> query = em.createNamedQuery("Producto.buscarPorNombre", Producto.class);
                query.setParameter("nombre", "%" + nombre + "%"); // Usar LIKE con comodines
                return query.getResultList();
                
                //Si solo la unidad de medida no esta vacia
            } else if (tipo != null && !tipo.toString().trim().isEmpty()) {
                TypedQuery<Producto> query = em.createNamedQuery("Producto.buscarPorTipo", Producto.class);
                query.setParameter("tipo", tipo);
                return query.getResultList();
                //Si ambos campos estan vacíos, se devuelven todos los ingredientes
            } else {
                // Ambos parámetros son nulos o vacíos: obtener todos los ingredientes
                TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
                return query.getResultList();
            }
            
        }catch(NoResultException e){
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        }finally{            
            //fin. Cerramos el entityManager
            em.close();
        }
    }

}
