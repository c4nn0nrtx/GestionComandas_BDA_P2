/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.productos;

import DTOs.nuevos.ProductoNuevoDTO;
import DTOs.viejos.ProductoViejoDTO;
import ENUMs.TipoProducto;
import entidades.Producto;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public interface IProductoBO 
{
    ProductoViejoDTO agregarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException;

    boolean deshabilitarProducto(Long id) throws NegocioException;

    ProductoViejoDTO modificarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException;

    ProductoViejoDTO obtenerProductoPorId(Long id) throws NegocioException; 

    List<ProductoViejoDTO> obtenerTodos() throws NegocioException;

    List<ProductoViejoDTO> buscarPorNombre(String nombre) throws NegocioException;

    List<ProductoViejoDTO> buscarPorTipo(String tipo) throws NegocioException;
    
    List<ProductoViejoDTO> obtenerPorTipo(TipoProducto tipoProducto) throws NegocioException;
    
    List<ProductoViejoDTO> obtenerPorFiltro(String nombre, TipoProducto tipo) throws NegocioException;

}
