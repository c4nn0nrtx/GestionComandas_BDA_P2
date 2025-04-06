/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.ingredientes;

import ENUMs.TipoProducto;
import entidades.Producto;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public interface IProductoDAO 
{
    public void agregarProducto(Producto producto) throws PersistenciaException;

    public void actualizarProducto(Producto producto) throws PersistenciaException;

    public void eliminarProducto(Long idProducto) throws PersistenciaException;

    public Producto buscarPorId(Long idProducto) throws PersistenciaException;

    public List<Producto> buscarPorNombre(String nombre) throws PersistenciaException;

    public List<Producto> buscarPorTipo(TipoProducto tipo);

    public boolean existeProductoConNombre(String nombre);

    public boolean validarDisponibilidadIngredientes(Long idProducto);

    public List<Producto> obtenerProductosActivos() throws PersistenciaException;

}
