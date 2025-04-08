/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.productos;

import DTOs.nuevos.ProductoIngredienteNuevoDTO;
import DTOs.viejos.ProductoIngredienteViejoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public interface IProductoIngredienteBO 
{
    ProductoIngredienteViejoDTO agregarRelacion(ProductoIngredienteNuevoDTO productoIngredienteNuevo) throws NegocioException;

    boolean eliminarRelacion(Long id) throws NegocioException;

    List<ProductoIngredienteViejoDTO> obtenerPorProducto(Long idProducto) throws NegocioException;
}
