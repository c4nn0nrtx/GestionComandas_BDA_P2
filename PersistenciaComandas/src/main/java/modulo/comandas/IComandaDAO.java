/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import ENUMs.EstadoComanda;
import entidades.Comanda;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Beto_
 */
public interface IComandaDAO {
    public Comanda agregar(Comanda comanda) throws PersistenciaException;
    
    public boolean eliminar(Long id) throws PersistenciaException;
    
    public Comanda actualizar(Comanda comanda) throws PersistenciaException;
    
    public Comanda obtenerPorId(Long id) throws PersistenciaException;
    
    public Comanda obtenerPorFolio(String folio) throws PersistenciaException;
    
    public List<Comanda> obtenerTodos() throws PersistenciaException;
    
    public boolean asignarCliente(Long idComanda, Long idCliente) throws PersistenciaException;
    
    public boolean desasignarCliente(Long idComanda) throws PersistenciaException;
}
