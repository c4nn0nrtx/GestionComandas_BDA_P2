/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import DTOs.nuevos.ComandaNuevoDTO;
import DTOs.viejos.ComandaViejoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Beto_
 */
public interface IComandaBO {
    public ComandaViejoDTO agregar(ComandaNuevoDTO comandaNueva) throws NegocioException;
    
    public boolean eliminar(Long id) throws NegocioException;
    
    public ComandaViejoDTO actualizar(ComandaViejoDTO comandaVieja) throws NegocioException;
    
    public ComandaViejoDTO actualizarEstado(ComandaViejoDTO comandaVieja) throws NegocioException;
    
    public ComandaViejoDTO obtenerPorId(Long id) throws NegocioException;
    
    public ComandaViejoDTO obtenerPorFolio(String folio) throws NegocioException;
    
    public List<ComandaViejoDTO> obtenerTodos() throws NegocioException;
    
    public boolean asignarCliente(Long idComanda, Long idCliente) throws NegocioException;
    
    public boolean desasignarCliente(Long idComanda) throws NegocioException;
}
