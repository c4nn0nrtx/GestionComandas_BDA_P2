/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.ingredientes;

import ENUMs.UnidadMedida;
import conexionBD.Conexion;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Beto_
 */
public class IngredienteDAO implements IIngredienteDAO{
    private static IngredienteDAO instanceIngredienteDAO;

    private IngredienteDAO() {
    }
    
    public static IngredienteDAO getInstanceDAO() {
        if (instanceIngredienteDAO == null) {
            instanceIngredienteDAO = new IngredienteDAO();
        }
        return instanceIngredienteDAO;
    }
    
    @Override
    public Ingrediente agregar(Ingrediente ingrediente) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        
        try {
            //1. Iniciamos la transacción
            em.getTransaction().begin();
            
            //2. Persistimos
            em.persist(ingrediente);
            
            //3. Finalizamos la transacción
            em.getTransaction().commit();
            return ingrediente;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();
            
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al guardar el ingrediente: " + e.getMessage());
        } finally {
            //fin. Independiente del resultado, se cierra el entityManager
            em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        
        try {
            //1. Iniciamos la transacción
            em.getTransaction().begin();
            
            //2. obtener por id
            Ingrediente ingredienteBuscado = em.find(Ingrediente.class, id);
            
            //3. mezclamos (actualizamos)
            em.remove(ingredienteBuscado);
            
            //4. Finalizamos la transacción
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();
            
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al guardar el ingrediente: " + e.getMessage());
        } finally {
            //fin. Independiente del resultado, se cierra el entityManager
            em.close();
        }
    }

    @Override
    public Ingrediente actualizar(Ingrediente ingrediente) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        
        try {
            //1. Iniciamos la transacción
            em.getTransaction().begin();
            
            //2. mezclamos (actualizamos)
            Ingrediente ingredienteNuevo = em.merge(ingrediente);
            
            //3. Finalizamos la transacción
            em.getTransaction().commit();
            return ingredienteNuevo;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();
            
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al guardar el ingrediente: " + e.getMessage());
        } finally {
            //fin. Independiente del resultado, se cierra el entityManager
            em.close();
        }
    }

    @Override
    public Ingrediente obtenerPorId(Long id) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        try {
            //1. retornamos el resultado obtenido
            return em.find(Ingrediente.class, id);
        } catch (Exception e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontró ningun profesor con el ID: " + id + ". " + e.getMessage());
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    @Override
    public List<Ingrediente> obtenerTodos() throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        try {
            //1. retornamos el resultado obtenido
            return em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class).getResultList();
        } catch (Exception e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al obtener todos los ingredientes: " + e.getMessage());
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    @Override
    public List<Ingrediente> obtenerPorNombre(String nombre) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try{            
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("Ingrediente.buscarPorNombre", Ingrediente.class);
            
            // 2. Añadimos parametros
            consulta.setParameter("nombre", "%" + nombre + "%");

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
    public List<Ingrediente> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try{            
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("Ingrediente.buscarPorUnidadMedida", Ingrediente.class);
            
            // 2. Añadimos parametros
            consulta.setParameter("unidadMedida", unidadMedida);
            
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
    
}
