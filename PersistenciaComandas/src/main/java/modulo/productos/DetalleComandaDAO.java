/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.productos;

import conexionBD.Conexion;
import entidades.DetalleComanda;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maximiliano
 */
public class DetalleComandaDAO implements IDetalleComandaDAO
{
    private static DetalleComandaDAO instance;

    private DetalleComandaDAO() {
    
    }

    public static DetalleComandaDAO getInstance() {
        if (instance == null) {
            instance = new DetalleComandaDAO();
        }
        return instance;
    }

    @Override
    public DetalleComanda agregar(DetalleComanda detalle) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(detalle);
            em.getTransaction().commit();
            return detalle;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al agregar detalle de comanda: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public DetalleComanda actualizar(DetalleComanda detalle) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            DetalleComanda actualizado = em.merge(detalle);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar detalle de comanda: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<DetalleComanda> obtenerPorIdComanda(Long idComanda) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<DetalleComanda> query = em.createNamedQuery("DetalleComanda.buscarPorComanda", DetalleComanda.class);
            query.setParameter("idComanda", idComanda);
            return query.getResultList();
        } catch (NoResultException e) {
            throw new PersistenciaException("No se encontraron detalles para la comanda con ID: " + idComanda);
        } finally {
            em.close();
        }
    }

    @Override
    public List<DetalleComanda> obtenerPorIdProducto(Long idProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<DetalleComanda> query = em.createNamedQuery("DetalleComanda.buscarPorProducto", DetalleComanda.class);
            query.setParameter("idProducto", idProducto);
            return query.getResultList();
        } catch (NoResultException e) {
            throw new PersistenciaException("No se encontraron detalles con el producto ID: " + idProducto);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            DetalleComanda detalle = em.find(DetalleComanda.class, id);
            if (detalle != null) {
                em.remove(detalle);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                throw new PersistenciaException("No se encontró el detalle de comanda con ID: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar el detalle de comanda: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
