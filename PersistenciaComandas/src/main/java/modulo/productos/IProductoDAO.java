/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.productos;

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
    public Producto agregar(Producto producto) throws PersistenciaException;

    public Producto actualizar(Producto producto) throws PersistenciaException;

    public boolean deshabilitar(Long id) throws PersistenciaException;

    public Producto obtenerPorId(Long id) throws PersistenciaException;

    public List<Producto> obtenerTodos() throws PersistenciaException;

    public List<Producto> buscarPorNombre(String nombre) throws PersistenciaException;

    public List<Producto> buscarPorNombreOCategoria(String filtro) throws PersistenciaException;
    
    public List<Producto> obtenerPorTipo(TipoProducto tipo) throws PersistenciaException;
    
    public List<Producto> obtenerPorFiltro(String nombre, TipoProducto tipo) throws PersistenciaException;

}
