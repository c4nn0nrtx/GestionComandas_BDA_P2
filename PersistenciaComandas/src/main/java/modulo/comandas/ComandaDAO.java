/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.comandas;

import conexionBD.Conexion;
import entidades.Cliente;
import entidades.Comanda;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Beto_
 */
public class ComandaDAO implements IComandaDAO{
    private static ComandaDAO instanceComandaDAO;

    private ComandaDAO() {
    }
    
    /**
     *
     * @return
     */
    public static ComandaDAO getInstanceDAO() {
        if (instanceComandaDAO == null) {
            instanceComandaDAO = new ComandaDAO();
        }
        return instanceComandaDAO;
    }

    @Override
    public Comanda agregar(Comanda comanda) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        
        try {
            //1. Iniciamos la transacción
            em.getTransaction().begin();
            
            //2. Persistimos
            em.persist(comanda);
            
            //3. Finalizamos la transacción
            em.getTransaction().commit();
            return comanda;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();
            
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al guardar la comanda: " + e.getMessage());
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
            Comanda comandaBuscada = em.find(Comanda.class, id);
            
            //3. eliminamos
            em.remove(comandaBuscada);
            
            //4. Finalizamos la transacción
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();
            
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al eliminar la comanda: " + e.getMessage());
        } finally {
            //fin. Independiente del resultado, se cierra el entityManager
            em.close();
        }
    }

    @Override
    public Comanda actualizar(Comanda comanda) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        
        try {
            //1. Iniciamos la transacción
            em.getTransaction().begin();
            
            //2. mezclamos (actualizamos)
            Comanda ingredienteNuevo = em.merge(comanda);
            
            //3. Finalizamos la transacción
            em.getTransaction().commit();
            return ingredienteNuevo;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();
            
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al actualizar el ingrediente: " + e.getMessage());
        } finally {
            //fin. Independiente del resultado, se cierra el entityManager
            em.close();
        }
    }

    @Override
    public Comanda obtenerPorId(Long id) throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        try {
            //1. retornamos el resultado obtenido
            return em.find(Comanda.class, id);
        } catch (Exception e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontró ninguna comanda con el ID: " + id + ". " + e.getMessage());
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }
    
    @Override
    public Comanda obtenerPorFolio(String folio) throws PersistenciaException{
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try{            
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("Comanda.buscarPorFolio", Comanda.class);
            
            // 2. Añadimos parametros
            consulta.setParameter("folio", folio);

            // 3. validamos resultado de la consulta y devolvemos
            return (Comanda) consulta.getSingleResult();
            
        }catch(NoResultException e){
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        }finally{            
            //fin. Cerramos el entityManager
            em.close();
        }
    }
    
    @Override
    public List<Comanda> obtenerPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException{
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try{                        
            if(fechaInicio == null || fechaFin == null){
                //1. retornamos el resultado obtenido
                return em.createQuery("SELECT c FROM Comanda c", Comanda.class).getResultList();
            }else{
                // 1. Creamos la consulta de fechas
                TypedQuery consulta = em.createNamedQuery("Comanda.buscarPorRangoFechas", Comanda.class);
                
                // 2. Añadimos parametros
                consulta.setParameter("fechaInicio", fechaInicio);
                consulta.setParameter("fechaFin", fechaFin);
                
                // 3. validamos resultado de la consulta y devolvemos
                return consulta.getResultList();
            }
            
        }catch(NoResultException e){
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        }finally{            
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    @Override
    public List<Comanda> obtenerTodos() throws PersistenciaException {
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        try {
            //1. retornamos el resultado obtenido
            return em.createQuery("SELECT c FROM Comanda c", Comanda.class).getResultList();
        } catch (Exception e) {
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al obtener todos las comandas: " + e.getMessage());
        } finally {
            //fin. Cerramos el entityManager
            em.close();
        }
    }

    @Override
    public boolean asignarCliente(Long idComanda, Long idCliente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Comanda comanda = em.find(Comanda.class, idComanda); // buscamos la comanda
            Cliente cliente = em.find(Cliente.class, idCliente); // buscamos al client

            if (comanda == null) {
                throw new PersistenciaException("comanda no encontrada.");
            }
            
            if (cliente == null) {
                throw new PersistenciaException("cliente no encontrado.");
            }

            // Si la comanda ya tiene un cliente lo quitamos a agregamos al nuevo
            // sobreescribimos vaya
            if (comanda.getCliente() != null) {
                Cliente clienteAnterior = comanda.getCliente();
                clienteAnterior.getComandas().remove(comanda);
                em.merge(clienteAnterior); // Actualizamos el cliente anterior
            }

            // Asignar el nuevo profesor
            comanda.setCliente(cliente);
            cliente.getComandas().add(comanda);
            
            //actualizar comanda y cliente
            em.merge(comanda);
            em.merge(cliente);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al asignar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean desasignarCliente(Long idComanda) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Comanda comanda = em.find(Comanda.class, idComanda);

            if (comanda == null) {
                throw new PersistenciaException("comanda no encontrada.");
            }

            if (comanda.getCliente() != null) {
                Cliente clienteAnterior = comanda.getCliente();
                clienteAnterior.getComandas().remove(comanda);
                em.merge(clienteAnterior); // Actualizamos el cliente anterior
            }

            comanda.setCliente(null);
            em.merge(comanda);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al desasignar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
