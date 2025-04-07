/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo;

import DTOs.nuevos.DetalleComandaNuevoDTO;
import DTOs.viejos.DetalleComandaViejoDTO;
import entidades.DetalleComanda;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public interface IDetalleComandaBO 
{
    DetalleComanda agregarDetalleComanda(DetalleComandaNuevoDTO detalleNuevo) throws NegocioException;

    DetalleComanda modificarDetalleComanda(DetalleComandaViejoDTO detalleViejo) throws NegocioException;

    boolean eliminarDetalleComanda(Long id) throws NegocioException;

    List<DetalleComandaViejoDTO> obtenerPorIdComanda(Long idComanda) throws NegocioException;

    List<DetalleComandaViejoDTO> obtenerPorIdProducto(Long idProducto) throws NegocioException;   
}
