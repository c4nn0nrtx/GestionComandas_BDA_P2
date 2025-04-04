/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.ingredientes;

import ENUMs.UnidadMedida;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Beto_
 */
public interface IIngredienteDAO {
    public Ingrediente agregar(Ingrediente ingrediente) throws PersistenciaException;
    
    public boolean eliminar(Long id) throws PersistenciaException;
    
    public Ingrediente actualizar(Ingrediente ingrediente) throws PersistenciaException;
    
    public Ingrediente obtenerPorId(Long id) throws PersistenciaException;
    
    public List<Ingrediente> obtenerTodos() throws PersistenciaException;
    
    public List<Ingrediente> obtenerPorNombre(String nombre) throws PersistenciaException;
    
    public List<Ingrediente> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws PersistenciaException;
}
