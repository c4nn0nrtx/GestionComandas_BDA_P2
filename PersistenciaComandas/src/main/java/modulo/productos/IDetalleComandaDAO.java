/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.productos;

import entidades.DetalleComanda;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public interface IDetalleComandaDAO 
{
    public DetalleComanda agregar(DetalleComanda detalle) throws PersistenciaException;

    public DetalleComanda actualizar(DetalleComanda detalle) throws PersistenciaException;

    public List<DetalleComanda> obtenerPorIdComanda(Long idComanda) throws PersistenciaException;

    public List<DetalleComanda> obtenerPorIdProducto(Long idProducto) throws PersistenciaException;

    public boolean eliminar(Long id) throws PersistenciaException;
}
