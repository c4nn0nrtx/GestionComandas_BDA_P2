/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.comandas;

import conexionBD.Conexion;
import entidades.Mesa;
import excepciones.PersistenciaException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Beto_
 */
public class MesaDAO implements IMesaDAO{
    private static MesaDAO instanceMesaDAO;

    private MesaDAO() {
    }
    
    public static MesaDAO getInstanceDAO() {
        if (instanceMesaDAO == null) {
            instanceMesaDAO = new MesaDAO();
        }
        return instanceMesaDAO;
    }
    
    @Override
    public Mesa agregar(Mesa mesa) throws PersistenciaException{
        //0. Crear la conexión
        EntityManager em = Conexion.crearConexion();
        
        try {
            //1. Iniciamos la transacción
            em.getTransaction().begin();
            
            //2. Persistimos
            em.persist(mesa);
            
            //3. Finalizamos la transacción
            em.getTransaction().commit();
            return mesa;
        } catch (Exception e) {
            //ex. Aplicamos rollback para deshacer la transacción
            em.getTransaction().rollback();
            
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("Error al guardar la mesa: " + e.getMessage());
        } finally {
            //fin. Independiente del resultado, se cierra el entityManager
            em.close();
        }
    }

    @Override
    public Mesa ObtenerPorNumeroMesa(Integer numeroMesa) throws PersistenciaException {
        // 0. Creamos el entityManager
        EntityManager em = Conexion.crearConexion();
        try{            
            // 1. Creamos la consulta
            TypedQuery consulta = em.createNamedQuery("Mesa.buscarPorNumeroMesa", Mesa.class);
            
            // 2. Añadimos parametros
            consulta.setParameter("numeroMesa", numeroMesa);

            // 3. validamos resultado de la consulta y devolvemos
            return (Mesa) consulta.getSingleResult();
            
        }catch(NoResultException e){
            //ex. Lanzamos una excepción de la capa
            throw new PersistenciaException("No se encontraron resultados");
        }finally{            
            //fin. Cerramos el entityManager
            em.close();
        }
    }
}
