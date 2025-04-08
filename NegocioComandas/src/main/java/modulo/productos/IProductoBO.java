/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.productos;

import DTOs.nuevos.ProductoNuevoDTO;
import DTOs.viejos.ProductoViejoDTO;
import entidades.Producto;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public interface IProductoBO 
{
    Producto agregarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException;

    boolean deshabilitarProducto(Long id) throws NegocioException;

    Producto modificarProducto(ProductoNuevoDTO productoNuevo) throws NegocioException;

    Producto obtenerProductoPorId(Long id) throws NegocioException;

    List<ProductoViejoDTO> obtenerTodos() throws NegocioException;

    List<ProductoViejoDTO> buscarPorNombre(String nombre) throws NegocioException;

    List<ProductoViejoDTO> buscarPorTipo(String tipo) throws NegocioException;
}
