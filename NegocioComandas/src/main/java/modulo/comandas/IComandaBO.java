/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import DTOs.nuevos.ComandaNuevoDTO;
import DTOs.viejos.ComandaViejoDTO;
import excepciones.NegocioException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Beto_
 */
public interface IComandaBO {

    /**
     *
     * @param comandaNueva
     * @return
     * @throws NegocioException
     */
    public ComandaViejoDTO agregar(ComandaNuevoDTO comandaNueva) throws NegocioException;
    
    /**
     *
     * @param id
     * @return
     * @throws NegocioException
     */
    public boolean eliminar(Long id) throws NegocioException;
    
    /**
     *
     * @param comandaVieja
     * @return
     * @throws NegocioException
     */
    public ComandaViejoDTO actualizar(ComandaViejoDTO comandaVieja) throws NegocioException;
    
    /**
     *
     * @param comandaVieja
     * @return
     * @throws NegocioException
     */
    public ComandaViejoDTO actualizarEstado(ComandaViejoDTO comandaVieja) throws NegocioException;
    
    /**
     *
     * @param id
     * @return
     * @throws NegocioException
     */
    public ComandaViejoDTO obtenerPorId(Long id) throws NegocioException;
    
    /**
     *
     * @param folio
     * @return
     * @throws NegocioException
     */
    public ComandaViejoDTO obtenerPorFolio(String folio) throws NegocioException;
    
    /**
     *
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws NegocioException
     */
    public List<ComandaViejoDTO> obtenerPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException;
    
    /**
     *
     * @return
     * @throws NegocioException
     */
    public List<ComandaViejoDTO> obtenerTodos() throws NegocioException;
    
    /**
     *
     * @param idComanda
     * @param idCliente
     * @return
     * @throws NegocioException
     */
    public boolean asignarCliente(Long idComanda, Long idCliente) throws NegocioException;
    
    /**
     *
     * @param idComanda
     * @return
     * @throws NegocioException
     */
    public boolean desasignarCliente(Long idComanda) throws NegocioException;
}
