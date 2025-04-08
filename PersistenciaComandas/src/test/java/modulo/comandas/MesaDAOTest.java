/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modulo.comandas;

import conexionBD.Conexion;
import entidades.Mesa;
import excepciones.PersistenciaException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Beto_
 */
@ExtendWith(MockitoExtension.class)
public class MesaDAOTest {
    @Mock
    private EntityManager em;
    
    @Mock
    private EntityTransaction transaction;
    
    @Mock
    private TypedQuery<Mesa> query;
    
    @InjectMocks
    private MesaDAO mesaDAO;
    
    private Mesa mesa1;
    private Mesa mesa2;
    
    @BeforeEach
    void setUp(){
        mesa1 = new Mesa(1);
        mesa2 = new Mesa(2);
    }
    
    @Test
    void testAgregar() throws PersistenciaException {
        try(MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)){
            
            //Simulación de la conexión y ejecución en el DAO
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.getTransaction()).thenReturn(transaction);
            Mesa result = mesaDAO.agregar(mesa1);
            
            //Verificar resultados
            assertEquals(mesa1, result);
            
            //Verificar que la conexion se abrio, guardo y se cerró
            verify(em).persist(mesa1);
            verify(transaction).begin();
            verify(transaction).commit();
            verify(em).close();
            
        }
    }
    
    @Test
    void testObtenerPorNumeroMesa() throws PersistenciaException {
        try(MockedStatic<Conexion> conexionMockeada = Mockito.mockStatic(Conexion.class)){
            
            //Simulación de la conexión y ejecución en el DAO
            conexionMockeada.when(Conexion::crearConexion).thenReturn(em);
            when(em.createNamedQuery("Mesa.buscarPorNumeroMesa", Mesa.class)).thenReturn(query);
            when(query.setParameter("numeroMesa", 2)).thenReturn(query);
            when(query.getSingleResult()).thenReturn(mesa2);
            Mesa result = mesaDAO.ObtenerPorNumeroMesa(2);
            
            //Verificar resultados
            assertEquals(mesa2, result);
            
            //Verificar que se llamó a la namedQuery y la conexión se cerró
            verify(em).createNamedQuery("Mesa.buscarPorNumeroMesa", Mesa.class);
            verify(query).setParameter("numeroMesa", 2);
            verify(query).getSingleResult();
            verify(em).close();
            
            //Otra verificacion para por si se abrieron transacciones
            //Al ser una lectura, no debería o causaría errores en cadena
            verify(transaction, never()).begin();
            verify(transaction, never()).commit();
        }
    }
}
