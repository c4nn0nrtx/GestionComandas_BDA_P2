/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBD;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Beto_
 */
public class Conexion {
    //El entityManager que estaremos reutilizando
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU");

     /**
     * Crea y devuelve una nueva instancia de EntityManager.
     * @return una nueva instancia de EntityManager para gestionar las operaciones con la base de datos.
     */
    public static EntityManager crearConexion() {    //Se obtiene un nuevo EntityManager en cada llamada a getEntityManager(), pero sin recrear la fábrica.
        return emf.createEntityManager(); // Se reutiliza la fábrica y se obtiene un nuevo EntityManager
    }

     /**
     * Cierra la instancia de EntityManagerFactory si está abierta,
     * liberando los recursos utilizados.
     */
    public static void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
